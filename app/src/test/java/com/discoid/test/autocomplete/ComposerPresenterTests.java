package com.discoid.test.autocomplete;

import com.discoid.test.autocomplete.composer.presenter.ComposerPresenter;
import com.discoid.test.autocomplete.composer.presenter.ComposerPresenterImpl;
import com.discoid.test.autocomplete.composer.view.ComposerView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

public class ComposerPresenterTests {

    private final String PICKED_STR = "Frogs";

    @Mock
    private ComposerView mComposerView;

    @Captor
    ArgumentCaptor<String> mStringCaptor;

    @Captor
    ArgumentCaptor<String[]> mStringArrayCaptor;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private final String mSuggestionList = "cats , dogs , pigs, hamsters";
    private final String[] mSuggestions = {"cats", "dogs", "pigs", "hamsters"};

    private ComposerPresenter mComposerPresenter;

    @Before
    public void setup() {
        mComposerPresenter = new ComposerPresenterImpl();
        mComposerPresenter.start(mComposerView);
    }

    @Test
    public void checkInstance() {
        assertNotNull(mComposerPresenter);
    }

    @Test
    public void checkBeingEdited() {
        mComposerPresenter.beingEdited();
        verify(mComposerView).clearMessage();
    }

    @Test
    public void checkCompleteValidText() {
        mComposerPresenter.complete(mSuggestionList);
        verify(mComposerView).makeChooseRequest(mStringArrayCaptor.capture());

        for (int strIndex = 0; strIndex < mSuggestions.length; strIndex++) {
            Assert.assertEquals(mSuggestions[strIndex], mStringArrayCaptor.getValue()[strIndex]);
        }
    }

    @Test
    public void checkCompleteInvalidText() {
        mComposerPresenter.complete("");
        verify(mComposerView).showNotAValidSuggestionList();
    }

    @Test
    public void checkPicked() {
        mComposerPresenter.picked(PICKED_STR);
        verify(mComposerView).showPickedMessage(mStringCaptor.capture());
        assertEquals(PICKED_STR,mStringCaptor.getValue());

    }

    @Test
    public void checkCancelledPicked() {
        mComposerPresenter.cancelledPick();
        verify(mComposerView).clearMessage();
    }

    @Test
    public void checkStop() {
        mComposerPresenter.stop();
        assertTrue(true);
    }
}


