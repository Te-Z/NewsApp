package com.zafindratafa.terence.mynews.Controllers.Activities;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.zafindratafa.terence.mynews.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.PositionAssertions.isCompletelyAbove;
import static android.support.test.espresso.assertion.PositionAssertions.isPartiallyAbove;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by maverick on 06/03/18.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityActivityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class);
    // Test if the activity is created
    @Test
    public void creationTest() throws Exception {
        onView(withId(R.id.activity_main_drawer_layout)).check(matches(isDisplayed()));
    }

    @Test
    public void toolBarDisplayTest() throws Exception {
        onView(withId(R.id.activity_main_toolbar)).perform(click());
    }

    @Test
    public void menuDisplayTest() throws Exception {
        onView(withId(R.id.menu_activity_main_search)).perform(click());
    }

    @Test
    public void isTopStoriesFragmentShowing() throws Exception {
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeRight());
        onView(withId(R.id.fragment_top_stories_title)).check(matches(isDisplayed()));
    }

    @Test
    public void isMostPopularFragmentShowing() throws Exception {
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.fragment_most_popular_title)).check(matches(isDisplayed()));
    }

    @Test
    public void isBusinessFragmentShowing() throws Exception {
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.activity_main_viewpager)).perform(swipeLeft());
        onView(withId(R.id.fragment_business_title)).check(matches(isDisplayed()));
    }
}