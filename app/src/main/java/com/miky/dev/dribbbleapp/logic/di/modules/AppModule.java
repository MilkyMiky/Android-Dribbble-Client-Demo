package com.miky.dev.dribbbleapp.logic.di.modules;


import android.content.Context;

import com.miky.dev.dribbbleapp.data.db.DataBaseHelper;
import com.miky.dev.dribbbleapp.ui.App;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
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

    @Provides
    @Singleton
    DataBaseHelper provideDatabaseHelper(Context context) {
        return new DataBaseHelper(context);
    }

}
