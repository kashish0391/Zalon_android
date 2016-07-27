package com.zalonstyles.app.zalon;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by KASHISH on 27-07-2016.
 */
public class mainActivityScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.service:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.bill:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
            case R.id.mar:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
            case R.id.set:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
            case R.id.rep:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
            case R.id.cal:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

