package com.elementalbraines.expressapp;

/**
 * Created by Maurel on 26/11/2017.
 */

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}