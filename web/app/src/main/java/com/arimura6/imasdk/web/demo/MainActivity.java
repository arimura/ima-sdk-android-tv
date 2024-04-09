package com.arimura6.imasdk.web.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up Firebase Remote Config
        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        remoteConfig.setConfigSettingsAsync(configSettings);
        remoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            remoteConfig.getAll();
                        } else {
                        }
                    }
                });

//        WebView.setWebContentsDebuggingEnabled(true);
//
//        WebView myWebView = (WebView) findViewById(R.id.webview);
//        myWebView.getSettings().setJavaScriptEnabled(true);
//        myWebView.getSettings().setMediaPlaybackRequiresUserGesture(false);
//
//        myWebView.setWebChromeClient(new WebChromeClient() {
////            @Override
////            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
////                Log.d("WebView", message + " -- From line " + lineNumber + " of " + sourceID);
////            }
//            @Override
//            public Bitmap getDefaultVideoPoster() {
//                return Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888);
//            }
//
//            @Override
//            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
//                Log.d("WebView", consoleMessage.message() + " -- From line "
//                        + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId());
//                return true;
//            }
//        });

//        myWebView.loadUrl("file:///android_asset/index.html");


    }
}