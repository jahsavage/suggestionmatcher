package com.discoid.test.autocomplete.composer.di;

import com.discoid.test.autocomplete.composer.view.ComposerActivity;
import com.discoid.test.autocomplete.di.scopes.PerActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {ComposerModule.class})
@PerActivity
public interface ComposerComponent {

    void inject(ComposerActivity composerActivity);
}
