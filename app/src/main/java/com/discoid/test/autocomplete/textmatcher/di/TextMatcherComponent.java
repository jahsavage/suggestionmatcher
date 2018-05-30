package com.discoid.test.autocomplete.textmatcher.di;

import com.discoid.test.autocomplete.di.scopes.PerActivity;
import com.discoid.test.autocomplete.textmatcher.view.TextMatcherActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {TextMatcherModule.class})
@PerActivity
public interface TextMatcherComponent {

    void inject(TextMatcherActivity textMatcherActivity);
}
