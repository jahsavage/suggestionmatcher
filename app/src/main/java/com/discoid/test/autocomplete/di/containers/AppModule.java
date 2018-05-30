package com.discoid.test.autocomplete.di.containers;

import android.app.Application;
import android.content.Context;

import com.discoid.test.autocomplete.di.IScheduler;
import com.discoid.test.autocomplete.di.LiveScheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jahsavage on 29/05/2018.
 */
@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @Singleton
    IScheduler provideScheduler() {
        return new LiveScheduler();
    }

}