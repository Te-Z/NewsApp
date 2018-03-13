package com.zafindratafa.terence.mynews.Controllers.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.zafindratafa.terence.mynews.Adapters.PageAdapter;
import com.zafindratafa.terence.mynews.R;


import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure Toolbar
        this.configureToolbar();
        // Configure ViewPager
        this.configureViewPagerAndTabs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    // ---------------
    // CONFIGURATION
    // ---------------

    private void configureToolbar(){
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }

    private void configureViewPagerAndTabs(){
        // Configure ViewPager
        ViewPager pager = (ViewPager) findViewById(R.id.activity_main_viewpager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        // Configure Tabs
        TabLayout tabs = (TabLayout) findViewById(R.id.activity_main_tabs);
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }
}
