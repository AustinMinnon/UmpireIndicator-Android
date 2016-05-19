package com.example.guest.umpireindicator;

import android.os.Build;
import android.widget.TextView;

import com.example.guest.umpireindicator.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

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
}
