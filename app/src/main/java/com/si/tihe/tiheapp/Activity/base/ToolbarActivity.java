package com.si.tihe.tiheapp.activity.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.si.tihe.tiheapp.R;


/**
 * Created on 11.5.2016.
 *
 * Toolbar activity
 *
 * @author Mišel Mojzeš
 */
public abstract class ToolbarActivity extends BaseActivity {

    private Toolbar toolbar;
    private LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        initView();

        // Attach the child layout to the root element
        LayoutInflater.from(this).inflate(getLayoutResId(), root, true);

        onCreateEx(savedInstanceState);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        root = (LinearLayout) findViewById(R.id.root);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void setTitle(int titleId) {
        getSupportActionBar().setTitle(titleId);
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    protected abstract void onCreateEx(Bundle savedInstanceState);

    protected abstract int getLayoutResId();

}
