package com.discoid.test.autocomplete.composer.presenter;


import com.discoid.test.autocomplete.composer.view.ComposerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComposerPresenterImpl implements ComposerPresenter {

    private ComposerView mComposerView;

    @Override
    public void start(ComposerView view) {
        mComposerView = view;
    }

    @Override
    public void complete(String text) {
        String suggestionArr[] = getSuggestionArr(text);
        if (suggestionArr == null) {
            mComposerView.showNotAValidSuggestionList();
        } else {
            mComposerView.makeChooseRequest(suggestionArr);
        }
    }

    @Override
    public void beingEdited() {
        mComposerView.clearMessage();
    }

    @Override
    public void picked(String picked) {
        mComposerView.showPickedMessage(picked);
    }

    @Override
    public void cancelledPick() {
        mComposerView.clearMessage();
    }

    private String[] getSuggestionArr(String text) {
        if (text == null || text.length() == 0) {
            return null;
        } else {
            List<String> csvList = new ArrayList<String>(Arrays.asList(text.split(",")));
            String strArr[] = new String[csvList.size()];
            int strIndex = 0;
            for (String suggestion : csvList) {
                strArr[strIndex++] = suggestion.trim();
            }
            return strArr;
        }
    }

    @Override
    public void stop() {
        mComposerView = null;
    }
}
