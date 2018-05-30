package com.discoid.test.autocomplete.textmatcher.presenter;

import com.discoid.test.autocomplete.textmatcher.view.TextMatcherView;

public interface TextMatcherPresenter {

    void start(TextMatcherView textMatcherView);

    void start(TextMatcherView textMatcherView, String[] suggestions);

    void suggestionSelected(String text);

    void stop();
}