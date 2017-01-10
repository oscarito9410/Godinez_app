package com.oscarito.godinez;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class NavegacionTest {

    @Rule
    public ActivityTestRule<Navegacion> mActivityTestRule = new ActivityTestRule<>(Navegacion.class);

    @Test
    public void navegacionTest() {
    }

}
