package com.zalonstyles.app.zalon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by KASHISH on 27-07-2016.
 */
public class CalendarMain extends AppCompatActivity {
    private WebView webView;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);
        webView = (WebView) findViewById(R.id.webview);
        SharedPreferences mSharedPreference= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        final String value=(mSharedPreference.getString("AppConstant.AUTH_TOKEN", "DEFAULT"));
        webView.getSettings().setJavaScriptEnabled(true);
        //Inject WebAppInterface methods into Web page by having Interface name 'Android'

       webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.clearCache(true);
        webView.loadUrl("file:///android_asset/calendar/index.html"+"?access_token="+value);
        webView.addJavascriptInterface(new WebAppInterface(this), "Android");

        Log.v("testweb","http://zalonstyle.in/calendar-new/index.html"+"?access_token="+value);


    }

    //Class to be injected in Web page
    public class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void moveToNextScreen(int test) {
        Log.v("test11", String.valueOf(test));
            Intent chnIntent = new Intent(CalendarMain.this, BillingMain.class);
            startActivity(chnIntent);

        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);

        // Save the state of the WebView
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        // Restore the state of the WebView
        webView.restoreState(savedInstanceState);
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
                Intent intent = new Intent(CalendarMain.this,ServicesMain.class);
                startActivity(intent);

                return true;

            case R.id.bill:
                Intent intent1 = new Intent(CalendarMain.this,BillingMain.class);
                startActivity(intent1);
                return true;
            case R.id.mar:
                Intent intent2 = new Intent(CalendarMain.this,MarketingMain.class);
                startActivity(intent2);
                return true;
            case R.id.set:
                Intent intent3 = new Intent(CalendarMain.this,SettingsMain.class);
                startActivity(intent3);
                return true;
            case R.id.rep:
                Intent intent4 = new Intent(CalendarMain.this,ReportMain.class);
                startActivity(intent4);
                return true;
            case R.id.cal:
                Intent intent5 = new Intent(CalendarMain.this,CalendarMain.class);
                startActivity(intent5);
                return true;
            case R.id.acccounting:
                Intent intent6 = new Intent(CalendarMain.this,AccountingMain.class);
                startActivity(intent6);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
