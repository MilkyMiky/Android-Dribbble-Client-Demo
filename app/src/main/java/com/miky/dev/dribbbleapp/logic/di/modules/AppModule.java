package com.miky.dev.dribbbleapp.logic.di.modules;


import android.content.Context;

import com.miky.dev.dribbbleapp.ui.App;

import javax.inject.Singleton;

import dagger.Provides;

public class AppModule {
    @Provides
    @Singleton
    App provideApp() {
        return App.getInstance();
    }

    @Provides
    @Singleton
    Context provideContext(App app) {
        return app.getApplicationContext();
    }

}
