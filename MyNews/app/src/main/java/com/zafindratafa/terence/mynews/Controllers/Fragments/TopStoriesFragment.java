package com.zafindratafa.terence.mynews.Controllers.Fragments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zafindratafa.terence.mynews.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment {

    public TopStoriesFragment() {}

    // Method that will create a new instance of TopStoriesFragment,
    // and add data to its Bundle
    public static TopStoriesFragment newInstance() {
        return new TopStoriesFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get layout of topStoriesFragment
        View result = inflater.inflate(R.layout.fragment_top_stories, container, false);

        return result;
    }

}
