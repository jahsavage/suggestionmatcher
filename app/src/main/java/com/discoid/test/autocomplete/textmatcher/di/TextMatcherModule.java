package com.discoid.test.autocomplete.textmatcher.di;

import com.discoid.test.autocomplete.di.IScheduler;
import com.discoid.test.autocomplete.textmatcher.presenter.TextMatcherPresenter;
import com.discoid.test.autocomplete.textmatcher.presenter.TextMatcherPresenterImpl;
import com.discoid.test.autocomplete.textmatcher.suggestions.SuggestionInteractor;
import com.discoid.test.autocomplete.textmatcher.suggestions.SuggestionInteractorImpl;
import com.discoid.test.autocomplete.textmatcher.view.TextMatcherActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class TextMatcherModule {

    private TextMatcherActivity mActivity;

    public TextMatcherModule(TextMatcherActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    SuggestionInteractor providesSuggestionInteractor() {
        return new SuggestionInteractorImpl();
    }

    @Provides
    TextMatcherPresenter providesTextMatcherPresenter(SuggestionInteractor suggestionInteractor, IScheduler scheduler) {
        return new TextMatcherPresenterImpl(suggestionInteractor, scheduler);
    }
}
