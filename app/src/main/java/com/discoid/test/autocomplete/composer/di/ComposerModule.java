package com.discoid.test.autocomplete.composer.di;

import com.discoid.test.autocomplete.composer.presenter.ComposerPresenter;
import com.discoid.test.autocomplete.composer.presenter.ComposerPresenterImpl;
import com.discoid.test.autocomplete.composer.view.ComposerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ComposerModule {

    private ComposerActivity mActivity;

    public ComposerModule(ComposerActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    ComposerPresenter providesComposerPresenter() {
        return new ComposerPresenterImpl();
    }
}
