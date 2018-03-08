package com.zafindratafa.terence.mynews.Controllers.Fragments;


import android.app.Fragment;
import android.os.Bundle;
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

    // Keys for our Bundle
    private static final String KEY_POSITION="position";
    private static final String KEY_COLOR="color";

    public TopStoriesFragment() {}

    // Method that will create a new instance of TopStoriesFragment,
    // and add data to its Bundle
    public static TopStoriesFragment newInstance(int position, int color) {
        // Create new fragment
        TopStoriesFragment topStoriesFragment = new TopStoriesFragment();

        // Create bundle and add it some data
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putInt(KEY_COLOR, color);
        topStoriesFragment.setArguments(args);

        return topStoriesFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get layout of topStoriesFragment
        View result = inflater.inflate(R.layout.fragment_top_stories, container, false);

        // Get widgets from layout and serialise it
        LinearLayout rootView = (LinearLayout) result.findViewById(R.id.fragment_top_stories_rootview);
        TextView textView = (TextView) result.findViewById(R.id.fragment_top_stories_title);

        // Get data from Bundle (created in method newInstance)
        int position = getArguments().getInt(KEY_POSITION, -1);
        int color = getArguments().getInt(KEY_COLOR, -1);

        // Update widgets with it
        rootView.setBackgroundColor(color);
        textView.setText("Page numéro "+position);

        Log.e(getClass().getSimpleName(), "onCreateView called for fragment number "+position);

        return result;
    }

}
