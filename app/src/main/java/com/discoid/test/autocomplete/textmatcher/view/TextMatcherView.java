package com.discoid.test.autocomplete.textmatcher.view;

public interface TextMatcherView {

    void loadSuggestions(String data[]);

    void returnResult(String text);
}
