package com.discoid.test.autocomplete.composer.view;

public interface ComposerView {
    void makeChooseRequest(String suggestion[]);

    void clearMessage();
    void showNotAValidSuggestionList();
    void showPickedMessage(String pickedString);
}
