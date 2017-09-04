package com.miky.dev.dribbbleapp.logic.di.components;

import com.miky.dev.dribbbleapp.logic.di.modules.AppModule;
import com.miky.dev.dribbbleapp.logic.di.modules.NetModule;
import com.miky.dev.dribbbleapp.ui.shots.ShotsPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    void inject(ShotsPresenter presenter);

}
