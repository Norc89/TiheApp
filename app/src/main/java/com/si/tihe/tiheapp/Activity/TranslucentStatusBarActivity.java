package com.si.tihe.tiheapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.si.tihe.tiheapp.R;


/**
 * Created on 5.11.2016.
 *
 * TranslucentStatusBarActivity
 *
 * @author Mišel Mojzeš
 */
public class TranslucentStatusBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translucent);
    }
}
