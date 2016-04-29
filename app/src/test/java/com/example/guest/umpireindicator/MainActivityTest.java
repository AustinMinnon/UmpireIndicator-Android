package com.example.guest.umpireindicator;

import android.content.Intent;
import android.os.Build;
import android.widget.TextView;

import com.example.guest.umpireindicator.ui.CoachActivity;
import com.example.guest.umpireindicator.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.Assert.assertTrue;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)
public class MainActivityTest {
    private MainActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }
    @Test
    public void validateTextViewContent() {
        TextView ballText = (TextView) activity.findViewById(R.id.ballText);
        assertTrue("Ball 0".equals(ballText.getText().toString()));
    }
    @Test
    public void secondActivityStarted() {
        activity.findViewById(R.id.coachButton).performClick();
        Intent expectedIntent = new Intent(activity, CoachActivity.class);
        ShadowActivity shadowActivity = org.robolectric.Shadows.shadowOf(activity);
        Intent actualIntent = shadowActivity.getNextStartedActivity();
        assertTrue(actualIntent.filterEquals(expectedIntent));
    }
}
