package com.si.tihe.tiheapp.Activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.inject.Key;
import com.si.tihe.tiheapp.R;

import java.util.HashMap;
import java.util.Map;

import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;
import roboguice.util.RoboContext;

/**
 * Created on 11.5.2016.
 *
 * Splash activity
 *
 * @author Mišel Mojzeš
 */
public class SplashActivity extends AppCompatActivity implements RoboContext {

    private static final int SPLASH_DELAY = 2500;
    private AsyncLoader asyncLoader;
    private long startTime;
    protected final Map<Key<?>, Object> scopedObjects = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startTime = System.currentTimeMillis();
        asyncLoader = new AsyncLoader();
        asyncLoader.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        asyncLoader = null;
    }

    @Override
    public Map<Key<?>, Object> getScopedObjectMap() {
        return scopedObjects;
    }

    /**
     * An AsyncTask to handle the loading.
     */
    private class AsyncLoader extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            // Set up a new thread since app.getBaseApplicationInjector() takes so long
            // Set the execution context for this thread in case the user
            // want to use the injector
            RoboGuice.setUseAnnotationDatabases(false);
            RoboGuice.getOrCreateBaseApplicationInjector(getApplication());

            RoboInjector injector = RoboGuice.getInjector(SplashActivity.this);
            injector.injectMembersWithoutViews(SplashActivity.this);
            injector.injectViewMembers(SplashActivity.this);

            long splashDuration = System.currentTimeMillis() - startTime;
            try {
                Thread.sleep(Math.max(SPLASH_DELAY - splashDuration, 0));
            } catch (InterruptedException e) {
                // ignored
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void param) {
            //startActivity(new Intent(SplashActivity.this, NavigationActivity.class));
            finish();
        }
    }
}
