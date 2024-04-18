package com.arimura6.imasdk.web.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiChangeReceiver extends BroadcastReceiver {
    private static final String TAG = "WifiChangeReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
            NetworkInfo networkInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
            if (networkInfo != null) {
                if (networkInfo.isConnected()) {
                    // Do something when connected to a WiFi network.
                    Log.i(TAG, "WiFi Connected: " + networkInfo.getExtraInfo());
                } else {
                    // Do something when disconnected from a WiFi network.
                    Log.i(TAG, "WiFi Disconnected");
                }
            }
        }
    }
}