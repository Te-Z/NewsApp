package com.zafindratafa.terence.mynews.Controllers.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zafindratafa.terence.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessFragment extends Fragment {

    public BusinessFragment() {}

    // Method that will create a new instance of BusinessFragment
    // and add data to its bundle
    public static BusinessFragment newInstance(){
        return new BusinessFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get layout of BusinessFragment
        View result = inflater.inflate(R.layout.fragment_business, container, false);

        return result;
    }

}
