package com.miky.dev.dribbbleapp.ui;


import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.miky.dev.dribbbleapp.logic.di.components.AppComponent;
import com.miky.dev.dribbbleapp.logic.di.components.DaggerAppComponent;

public class App extends Application {
    private static App instance;
    private AppComponent mAppComponent;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        instance = this;

        getComponent();
    }


    public AppComponent getComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .build();
        }
        return mAppComponent;
    }
}
