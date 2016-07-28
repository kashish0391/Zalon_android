package com.zalonstyles.app.zalon;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by KASHISH on 28-07-2016.
 */
public class AccountingMain extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accountingmain);
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
                Intent intent = new Intent(AccountingMain.this,ServicesMain.class);
                startActivity(intent);

                return true;

            case R.id.bill:
                Intent intent1 = new Intent(AccountingMain.this,BillingMain.class);
                startActivity(intent1);
                return true;
            case R.id.mar:
                Intent intent2 = new Intent(AccountingMain.this,MarketingMain.class);
                startActivity(intent2);
                return true;
            case R.id.set:
                Intent intent3 = new Intent(AccountingMain.this,SettingsMain.class);
                startActivity(intent3);
                return true;
            case R.id.rep:
                Intent intent4 = new Intent(AccountingMain.this,ReportMain.class);
                startActivity(intent4);
                return true;
            case R.id.cal:
                Intent intent5 = new Intent(AccountingMain.this,CalendarMain.class);
                startActivity(intent5);
                return true;
            case R.id.acccounting:
                Intent intent6 = new Intent(AccountingMain.this,AccountingMain.class);
                startActivity(intent6);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
