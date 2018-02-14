package com.example.tenzind.ominitiator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
/**
 * Created by tenzind on 11/29/2017.
 */

public class MainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        BottomBar  bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        //bottomBar.getTabWithId(R.id.servers).setSelected(false);
        bottomBar .setOnTabSelectListener(new OnTabSelectListener() {
            int i=0;
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (this.i != 0) {
                    if (tabId == R.id.servers) {
                        final Intent intentWelcome = new Intent(MainMenu.this, ServerOperations.class);
                        startActivity(intentWelcome);
                    }
                    /*if (tabId == R.id.order_list) {
                        final Intent intentWelcome = new Intent(MainMenu.this, Orders.class);
                        startActivity(intentWelcome);
                    }*/
                    if (tabId == R.id.orders) {
                        final Intent intentWelcome = new Intent(MainMenu.this, OrderOperations.class);
                        startActivity(intentWelcome);
                    }
                    if (tabId == R.id.tasks) {
                        final Intent intentWelcome = new Intent(MainMenu.this, Executions.class);
                        startActivity(intentWelcome);
                    }
                }
                this.i++;
            }
        });
    }
}
