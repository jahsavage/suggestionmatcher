package com.discoid.test.autocomplete.composer.presenter;

import com.discoid.test.autocomplete.composer.view.ComposerView;

public interface ComposerPresenter {

    void start(ComposerView view);
    void stop();

    void beingEdited();
    void complete(String text);
    void picked(String pickedStr);
    void cancelledPick();
}
