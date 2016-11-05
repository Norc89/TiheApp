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
        addNavigationItem(TranslucentStatusBarActivity.class, "TranslucentStatusBar", "On this screen is TranslucentStatusBar status bar", R.drawable.ic_home);
        addNavigationItem(SimpleActivity.class, "*Simple fragment", "This is demo how to create simple fragment", R.drawable.ic_fire);
        addNavigationItem(TabActivity.class, "*Tabs", "This screen shows how to use tabs", R.drawable.ic_head);
        addNavigationItem(CustomViewActivity.class, "Custom view +db", "This is simple demo of custom view and db use", R.drawable.ic_head);

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
