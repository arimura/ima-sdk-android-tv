package com.arimura6.imasdk.web.demo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyAccessibilityService extends Service {
    public MyAccessibilityService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}