package com.discoid.test.autocomplete;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.discoid.test.autocomplete.di.containers.AppModule;
import com.discoid.test.autocomplete.di.containers.ApplicationComponent;
import com.discoid.test.autocomplete.di.containers.DaggerApplicationComponent;
import com.discoid.test.autocomplete.di.inject.ComponentProvider;

import static timber.log.Timber.DebugTree;
import static timber.log.Timber.plant;

public class BaseApplication extends Application implements ComponentProvider<ApplicationComponent> {

    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        // Initialise debugger
        plant(new DebugTree());

        mAppComponent = initAppComponent();
    }

    protected ApplicationComponent initAppComponent() {
        return DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return mAppComponent;
    }

    public void setAppComponent(ApplicationComponent appComponent) {
        mAppComponent = appComponent;
    }

}
