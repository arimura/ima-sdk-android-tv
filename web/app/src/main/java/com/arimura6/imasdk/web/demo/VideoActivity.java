package com.arimura6.imasdk.web.demo;

import android.os.Bundle;

//import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentActivity;

public class VideoActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_video);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
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