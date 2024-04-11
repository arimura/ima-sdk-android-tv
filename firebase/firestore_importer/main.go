package main

import (
	"context"
	"encoding/json"
	"flag"
	"io"
	"log"
	"os"
	"time"

	"cloud.google.com/go/firestore"
	firebase "firebase.google.com/go"
	"google.golang.org/api/option"
)

type signage struct {
	Name      string   `json:"name"`
	Endpoints []string `json:"endpoints"`
}

func main() {
	serviceAccountKeyFilePath := flag.String("cred", "", "The file path to the service account key json file.")
	signageFilePath := flag.String("signage", "", "The file path to the signage json file.")
	projectID := flag.String("projectID", "", "The project ID.")
	flag.Parse()

	jsonFile, err := os.Open(*signageFilePath)
	if err != nil {
		log.Fatalf("error opening JSON file: %v\n", err)
	}
	defer jsonFile.Close()

	byteValue, err := io.ReadAll(jsonFile)
	if err != nil {
		log.Fatalf("error reading JSON file: %v\n", err)
	}

	var signages map[string]signage
	err = json.Unmarshal(byteValue, &signages)
	if err != nil {
		log.Fatalf("error unmarshalling JSON data: %v\n", err)
	}

	opt := option.WithCredentialsFile(*serviceAccountKeyFilePath)
	config := &firebase.Config{ProjectID: *projectID}
	ctx := context.Background()
	app, err := firebase.NewApp(ctx, config, opt)
	if err != nil {
		log.Fatalf("error initializing app: %v\n", err)
	}

	client, err := app.Firestore(ctx)
	if err != nil {
		log.Fatalln(err)
	}
	addDocAsMap(ctx, client, signages)
	defer client.Close()

}

func addDocAsMap(ctx context.Context, client *firestore.Client, siganes map[string]signage) error {
	for id, signage := range siganes {
		_, err := client.Collection("test").Doc(id).Set(ctx, map[string]interface{}{
			"name":      signage.Name,
			"endpoints": signage.Endpoints,
			"updatedAd": time.Now(),
		})
		if err != nil {
			log.Printf("An error has occurred: %s", err)
			return err
		}
	}
	return nil
}
