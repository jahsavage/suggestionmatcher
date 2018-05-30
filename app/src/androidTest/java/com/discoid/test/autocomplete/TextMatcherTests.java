package com.discoid.test.autocomplete;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.discoid.test.autocomplete.textmatcher.view.TextMatcherActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TextMatcherTests {

    private final String FISH_TYPES[] = {"Pike", "Carp", "Roach", "Rudd", "Ruddies", "Trout"};

    @Rule
    public ActivityTestRule<TextMatcherActivity> mActivityRule
            = new ActivityTestRule<TextMatcherActivity>(TextMatcherActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent intent = new Intent(targetContext, TextMatcherActivity.class);
            intent.setAction(Intent.ACTION_PICK);
            intent.putExtra(Intent.EXTRA_TEXT, FISH_TYPES);
            intent.setType("text/plain");
            return intent;
        }
    };

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.discoid.test.autocomplete", appContext.getPackageName());
    }

    @Test
    public void checkSuggestionPageDisplayed() {
        Espresso.onIdle();
        Espresso.onView(withId(R.id.autocomplete)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.autocomplete)).check(matches(withText("")));
    }

    @Test
    public void checkTextMatching() throws InterruptedException {
        Espresso.onIdle();
        Espresso.onView(withId(R.id.autocomplete)).perform(typeText("Pi"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.autocomplete)).check(matches(withText("Pi")));
        Espresso.onData(equalTo("Pike")).inRoot(RootMatchers.isPlatformPopup()).perform(ViewActions.click());

        Espresso.onIdle();
        Instrumentation.ActivityResult activityResult = mActivityRule.getActivityResult();
        assertEquals(Activity.RESULT_OK, activityResult.getResultCode());
        assertEquals("Pike", activityResult.getResultData().getStringExtra(Intent.EXTRA_TEXT));
    }

    @Test
    public void checkTwoMatches() throws InterruptedException {
        Espresso.onIdle();
        Espresso.onView(withId(R.id.autocomplete)).perform(typeText("Rud"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.autocomplete)).check(matches(withText("Rud")));
        Espresso.onData(equalTo("Rudd")).inRoot(RootMatchers.isPlatformPopup()).check(matches(isDisplayed()));
        Espresso.onData(equalTo("Ruddies")).inRoot(RootMatchers.isPlatformPopup()).check(matches(isDisplayed()));
    }

    private void waitForCallback() throws InterruptedException {
        Integer aObject = new Integer(3);
        synchronized(aObject) {
            aObject.wait(2000L);
        }
    }
}
