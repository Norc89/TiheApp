package com.si.tihe.tiheapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.si.tihe.tiheapp.R;
import com.si.tihe.tiheapp.activity.base.ToolbarActivity;
import com.si.tihe.tiheapp.recycler.items.NavigationItem;
import com.si.tihe.tiheapp.recycler.renderer.NavigationRenderer;

/**
 * Created on 5.11.2016.
 *
 * Navigation class
 *
 * @author Mišel Mojzeš
 */
public class NavigationActivity extends ToolbarActivity implements NavigationItem.OnNavigationItemClick {

    private NavigationRenderer renderer;

    @Override
    protected void onCreateEx(Bundle savedInstanceState) {
        initView();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        setTitle(getString(R.string.activity_navigation_title));
        addNavigationItem(NavigationActivity.class, "TranslucentStatusBar", "On this screen is TranslucentStatusBar status bar", R.drawable.ic_home);
        /*addNavigationItem(CustomViewActivity.class, "Custom view 2", "This is a demo how to use custom view", R.drawable.head);
        addNavigationItem(FragmentActivity.class, "Activity -> fragment", "This is demo how to use fragment on activity", R.drawable.fire);
        addNavigationItem(TabActivity.class, "*Tabs", "This is description of navigation 2, This is description of navigation 2, This is description of navigation 2", R.drawable.home);
        addNavigationItem(DemoActivity.class, "*View pager", "This is description of navigation 2, This is description of navigation 2, This is description of navigation 2", R.drawable.head);
        addNavigationItem(DemoActivity.class, "*Drawer with fragments", "This is description of navigation 2, This is description of navigation 2, This is description of navigation 2", R.drawable.fire);
        addNavigationItem(DemoActivity.class, "Navigation 4", "This is description of navigation 2, This is description of navigation 2, This is description of navigation 2", R.drawable.home);
        addNavigationItem(DemoActivity.class, "Navigation 2", "This is description of navigation 2, This is description of navigation 2, This is description of navigation 2", R.drawable.head);
        addNavigationItem(DemoActivity.class, "Navigation 3", "This is description of navigation 2, This is description of navigation 2, This is description of navigation 2", R.drawable.fire);
        addNavigationItem(StatsViewActivity.class, "StatsView", "This is description of navigation 2, This is description of navigation 2, This is description of navigation 2", R.drawable.home);
    */
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        renderer = new NavigationRenderer(recyclerView);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_navigation;
    }

    protected void addNavigationItem(Class<?> target, String titleText, String descriptionText, int imageRes) {
        renderer.addNavigationItem(new NavigationItem(target, titleText, descriptionText, imageRes, this));
    }

    @Override
    public void onNavigationItemClick(Class<?> targetActivity) {
        if (targetActivity != null) {
            Intent intent = new Intent(this, targetActivity);
            startActivity(intent);
        }
    }
}
