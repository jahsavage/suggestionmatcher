package com.discoid.test.autocomplete.textmatcher.suggestions;

import io.reactivex.Single;

public class SuggestionInteractorImpl implements SuggestionInteractor {

    private final String DEFAULT_SUGGESTIONS[] = {"hello", "helium", "height", "high", "horse", "fooer", "foreene"};

    @Override
    public Single<String[]> fetchSuggestions() {
        return Single.just(DEFAULT_SUGGESTIONS);
    }
}
