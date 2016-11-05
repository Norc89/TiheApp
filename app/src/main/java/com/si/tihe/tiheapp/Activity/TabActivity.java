package com.si.tihe.tiheapp.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.si.tihe.tiheapp.R;
import com.si.tihe.tiheapp.activity.base.ToolbarActivity;
import com.si.tihe.tiheapp.adapter.TabLayoutViewPagerAdapter;
import com.si.tihe.tiheapp.fragment.SimpleTextFragment;


/**
 * Created on 5.11.2016.
 *
 * Tab activity
 *
 * @author Mišel Mojzeš
 */
public class TabActivity extends ToolbarActivity {

    private static int NUMBER_OF_TABS = 3;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreateEx(Bundle savedInstanceState) {
        setupViewPager();
        setupTabs();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tab;
    }

    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.tab_activity_viewpager);
        TabLayoutViewPagerAdapter adapter = new TabLayoutViewPagerAdapter(getSupportFragmentManager());
        String number;
        for (int i = 1; i <= NUMBER_OF_TABS; ++i) {
            number = "tab:" + Integer.toString(i);
            adapter.addFragment(SimpleTextFragment.createInstance(number), number);
        }
        viewPager.setAdapter(adapter);
    }

    private void setupTabs() {
        tabLayout = (TabLayout) findViewById(R.id.tab_activity_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

}
