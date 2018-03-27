package com.zafindratafa.terence.mynews.Controllers.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.zafindratafa.terence.mynews.Controllers.Fragments.MostPopularFragment;
import com.zafindratafa.terence.mynews.Controllers.Fragments.ResultFragment;
import com.zafindratafa.terence.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionActivity extends AppCompatActivity {

    // For design
    @BindView(R.id.activity_section_toolbar)
    Toolbar mToolbar;

    // For data
    private static final String GET_SECTION = "GET_SECTION";
    private static final String SET_SECTION = "SET_SECTION";

    private String section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        ButterKnife.bind(this);

        this.configureToolbar();

        // Get data from SearchActivity
        section = getIntent().getStringExtra(GET_SECTION);

        sendData();
    }

    // --------------
    // CONFIGURATION
    // --------------

    private void configureToolbar(){
        // Add back button
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void sendData() {
        // Pack data in a bundle
        Bundle bundle = new Bundle();
        bundle.putString(SET_SECTION, section);

        // Pass over the bundle to our fragment
        Fragment sectionFragment = new MostPopularFragment();
        sectionFragment.setArguments(bundle);

        // Then show our fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.container, sectionFragment).commit();
    }
}