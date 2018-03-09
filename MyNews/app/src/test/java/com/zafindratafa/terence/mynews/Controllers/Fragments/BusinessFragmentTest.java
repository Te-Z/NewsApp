package com.zafindratafa.terence.mynews.Controllers.Fragments;



import android.support.v4.app.Fragment;

import com.zafindratafa.terence.mynews.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

/**
 * Created by maverick on 09/03/18.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class BusinessFragmentTest {

    @Test
    public void shouldNotBeNull() throws Exception {
        Fragment businessFragment = BusinessFragment.newInstance();
        startFragment(businessFragment);
        assertNotNull(businessFragment);
    }

}