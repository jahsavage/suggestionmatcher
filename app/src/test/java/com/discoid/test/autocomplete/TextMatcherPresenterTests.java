package com.discoid.test.autocomplete;

import com.discoid.test.autocomplete.di.IScheduler;
import com.discoid.test.autocomplete.di.TestScheduler;
import com.discoid.test.autocomplete.textmatcher.presenter.TextMatcherPresenter;
import com.discoid.test.autocomplete.textmatcher.presenter.TextMatcherPresenterImpl;
import com.discoid.test.autocomplete.textmatcher.suggestions.SuggestionInteractor;
import com.discoid.test.autocomplete.textmatcher.view.TextMatcherView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import io.reactivex.Single;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class TextMatcherPresenterTests {

    private TextMatcherPresenter mTextMatcherPresenter;

    @Mock
    private SuggestionInteractor mSuggestionInteractor;

    @Mock
    private TextMatcherView mTextMatcherView;

    private IScheduler mScheduler;

    @Captor
    ArgumentCaptor<String[]> mStringArrayCaptor;

    @Captor
    ArgumentCaptor<String> mStringCaptor;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private final String[] mSuggestions = {"cats", "dogs", "pigs", "hamsters"};
    private final Single<String[]> mSingleSuggestions = Single.just(mSuggestions);


    @Before
    public void setup() {
        mScheduler = new TestScheduler();
        mTextMatcherPresenter = new TextMatcherPresenterImpl(mSuggestionInteractor, mScheduler);
    }

    @Test
    public void checkInstance() {
        assertNotNull(mTextMatcherPresenter);
    }

    @Test
    public void checkDefaultSuggestions() {

        when(mSuggestionInteractor.fetchSuggestions()).thenReturn(mSingleSuggestions);

        mTextMatcherPresenter.start(mTextMatcherView);

        verify(mSuggestionInteractor).fetchSuggestions();
        verify(mTextMatcherView).loadSuggestions(mStringArrayCaptor.capture());
        assertEquals(mSuggestions.length, mStringArrayCaptor.getValue().length);

        for (int strIndex = 0; strIndex < mSuggestions.length; strIndex++) {
            assertEquals(mSuggestions[strIndex], mStringArrayCaptor.getValue()[strIndex]);
        }
    }

    @Test
    public void checkStartCallWithSuggestions() {

        mTextMatcherPresenter.start(mTextMatcherView, mSuggestions);
        verify(mTextMatcherView).loadSuggestions(mStringArrayCaptor.capture());
        assertEquals(mSuggestions.length, mStringArrayCaptor.getValue().length);

        for (int strIndex = 0; strIndex < mSuggestions.length; strIndex++) {
            assertEquals(mSuggestions[strIndex], mStringArrayCaptor.getValue()[strIndex]);
        }
    }

    @Test
    public void checkSuggestionSelecterd() {
        mTextMatcherPresenter.start(mTextMatcherView, mSuggestions);
        mTextMatcherPresenter.suggestionSelected(mSuggestions[1]);

        verify(mTextMatcherView).returnResult(mStringCaptor.capture());
        assertEquals(mSuggestions[1], mStringCaptor.getValue());
    }

    @Test
    public void checkStop() {
        mTextMatcherPresenter.start(mTextMatcherView, mSuggestions);
        mTextMatcherPresenter.stop();
    }
}