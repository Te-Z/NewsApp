package com.zafindratafa.terence.mynews.Controllers.Fragments;

import android.support.test.espresso.Espresso;
import android.support.v4.app.Fragment;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.zafindratafa.terence.mynews.Controllers.Activities.MainActivity;
import com.zafindratafa.terence.mynews.Models.TopStories.TopStories;
import com.zafindratafa.terence.mynews.R;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

/**
 * Created by maverick on 14/03/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TopStoriesFragmentAndroidTest {

    private Fragment mTopStoriesFragment;
    private MainActivity mTestActivity;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void isTopStoriesFragmentShowing() throws Exception {
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeRight());
        onView(withId(R.id.fragment_top_stories_swipe_container)).check(matches(isDisplayed()));
    }

    @Test
    public void isTopStoriesTabDisplayed() throws Exception {
        onView(withText("TOP STORIES")).perform(click());
        onView(withId(R.id.fragment_top_stories_swipe_container)).check(matches(isDisplayed()));
    }

    @Test
    public void topStoriesRecyclerViewTest() throws Exception {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.fragment_top_stories_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.activity_web_webview)).check(matches(isDisplayed()));
        Espresso.pressBack();
    }
}