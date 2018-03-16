package com.zafindratafa.terence.mynews.Controllers.Fragments;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.zafindratafa.terence.mynews.Controllers.Activities.MainActivity;
import com.zafindratafa.terence.mynews.R;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.zafindratafa.terence.mynews.Controllers.Fragments.SwipeRefreshMatchers.withCustomConstraints;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Created by maverick on 14/03/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BusinessFragmentAndroidTest {

    private Fragment mBusinessFragment;
    private MainActivity mTestActivity;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void isBusinessFragmentShowing() throws Exception {
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.fragment_business_swipe_container)).check(matches(isDisplayed()));
    }

    @Test
    public void isBusinessTabDisplayed() throws Exception {
        onView(withText("BUSINESS")).perform(click());
        onView(withId(R.id.fragment_business_swipe_container)).check(matches(isDisplayed()));
    }

    @Test
    public void businessRecyclerViewTest() throws Exception {
        onView(withText("BUSINESS")).perform(click());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.fragment_business_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.activity_web_layout)).check(matches(isDisplayed()));
        Espresso.pressBack();
    }

    @Test
    public void swipeRefreshBusinessLayout() throws Exception {
        mTestActivity = mMainActivityActivityTestRule.getActivity();
        mBusinessFragment = mTestActivity.getSupportFragmentManager().getFragments().get(0);
        mSwipeRefreshLayout = mBusinessFragment.getView().findViewById(R.id.fragment_top_stories_swipe_container);
        int count = 0;
        Boolean answer = false;

        while (!mSwipeRefreshLayout.isRefreshing() && count<5){
            onView(withId(R.id.fragment_top_stories_recycler_view)).perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)));
            if (mSwipeRefreshLayout.isRefreshing() == true){
                answer = true;
            } else {
                count++;
            }
        }
        assertTrue(answer == true);
    }
}