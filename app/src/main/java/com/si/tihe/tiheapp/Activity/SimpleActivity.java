package com.si.tihe.tiheapp.activity;

import android.os.Bundle;

import com.si.tihe.tiheapp.R;
import com.si.tihe.tiheapp.activity.base.ToolbarActivity;

/**
 * Created on 5.11.2016.
 *
 * Simple activity
 *
 * @author Mišel Mojzeš
 */
public class SimpleActivity extends ToolbarActivity {

    @Override
    protected void onCreateEx(Bundle savedInstanceState) {
        setTitle("Demo activity");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_simple;
    }
}
