package main

import (
	"context"
	"flag"
	"log"

	"cloud.google.com/go/firestore"
	firebase "firebase.google.com/go"
	"google.golang.org/api/option"
)

func main() {
	serviceAccountKeyFilePath := flag.String("cred", "", "The file path to the service account key json file.")
	projectID := flag.String("projectID", "", "The project ID.")
	flag.Parse()

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
	addDocAsMap(ctx, client)
	defer client.Close()

}

func addDocAsMap(ctx context.Context, client *firestore.Client) error {
	_, err := client.Collection("test").Doc("hoge").Set(ctx, map[string]interface{}{
		"name":    "Los Angeles",
		"state":   "CA",
		"country": "USA",
	})
	if err != nil {
		// Handle any errors in an appropriate way, such as returning them.
		log.Printf("An error has occurred: %s", err)
	}

	return err
}
