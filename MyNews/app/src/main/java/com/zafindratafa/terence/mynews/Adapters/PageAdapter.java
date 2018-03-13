package com.zafindratafa.terence.mynews.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zafindratafa.terence.mynews.Controllers.Fragments.BusinessFragment;
import com.zafindratafa.terence.mynews.Controllers.Fragments.MostPopularFragment;
import com.zafindratafa.terence.mynews.Controllers.Fragments.TopStoriesFragment;

/**
 * Created by maverick on 09/03/18.
 */

public class PageAdapter extends FragmentPagerAdapter {

    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return TopStoriesFragment.newInstance();
            case 1:
                return MostPopularFragment.newInstance();
            case 2:
                return BusinessFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "TOP STORIES";
            case 1:
                return "MOST POPULAR";
            case 2:
                return "BUSINESS";
            default:
                return null;
        }
    }
}
