package com.example.guest.umpireindicator;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class MainActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule= new ActivityTestRule<>(MainActivity.class);

    @Test
    public void validatePlusBall(){
        onView(withId(R.id.plusBall)).perform(click());
        onView(withId(R.id.ballText)).check(matches(withText("Ball 1")));
    }
    @Test
    public void validatePlusStrike(){
        onView(withId(R.id.plusOut)).perform(click());
        onView(withId(R.id.outText)).check(matches(withText("Out 1")));
    }
}