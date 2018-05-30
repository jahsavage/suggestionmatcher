package com.discoid.test.autocomplete.textmatcher.presenter;

import com.discoid.test.autocomplete.di.IScheduler;
import com.discoid.test.autocomplete.textmatcher.suggestions.SuggestionInteractor;
import com.discoid.test.autocomplete.textmatcher.view.TextMatcherView;

import io.reactivex.disposables.Disposable;

public class TextMatcherPresenterImpl implements TextMatcherPresenter {

    private TextMatcherView mTextMatcherView;
    private SuggestionInteractor mSuggestionInteractor;
    private IScheduler mScheduler;
    private Disposable mDisposable;
    private boolean mIsStandaloneVersion;

    public TextMatcherPresenterImpl(SuggestionInteractor suggestionInteractor, IScheduler scheduler) {
        mSuggestionInteractor = suggestionInteractor;
        mScheduler = scheduler;
    }

    @Override
    public void start(TextMatcherView textMatcherView) {
        mIsStandaloneVersion = true;
        mTextMatcherView = textMatcherView;
        loadSuggestions();
    }

    @Override
    public void start(TextMatcherView textMatcherView, String[] suggestions) {
        mIsStandaloneVersion = false;
        mTextMatcherView = textMatcherView;
        mTextMatcherView.loadSuggestions(suggestions);
    }

    private void loadSuggestions() {
        mDisposable = mSuggestionInteractor.fetchSuggestions()
                .subscribeOn(mScheduler.backgroundThread())
                .observeOn(mScheduler.mainThread())
                .subscribe(mTextMatcherView::loadSuggestions, this::dealWithError);

    }

    private void dealWithError(Throwable exception) {
        // @TODO decide how to report error
        // Should be only applicable when we plug in network access to suggestions
    }

    @Override
    public void suggestionSelected(String text) {
        if (!mIsStandaloneVersion) {
            mTextMatcherView.returnResult(text.trim());
        }
    }

    @Override
    public void stop() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }
        mTextMatcherView = null;
        mSuggestionInteractor = null;
        mScheduler = null;
    }


}