package com.discoid.test.autocomplete.di.containers;

import android.content.Context;

import com.discoid.test.autocomplete.composer.di.ComposerComponent;
import com.discoid.test.autocomplete.composer.di.ComposerModule;
import com.discoid.test.autocomplete.di.IScheduler;
import com.discoid.test.autocomplete.textmatcher.di.TextMatcherComponent;
import com.discoid.test.autocomplete.textmatcher.di.TextMatcherModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by jahsavage on 10/08/2016.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface ApplicationComponent {
    Context providesContext();
    IScheduler provideScheduler();

    TextMatcherComponent plus(TextMatcherModule textMatcherModule);
    ComposerComponent plus(ComposerModule composerModule);
}
