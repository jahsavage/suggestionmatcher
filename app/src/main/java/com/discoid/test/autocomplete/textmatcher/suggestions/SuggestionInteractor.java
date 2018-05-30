package com.discoid.test.autocomplete.textmatcher.suggestions;

import io.reactivex.Single;

public interface SuggestionInteractor {
    Single<String[]> fetchSuggestions();
}
