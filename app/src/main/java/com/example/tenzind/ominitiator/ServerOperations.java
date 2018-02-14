package com.example.tenzind.ominitiator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 *  Created by TenzinD on 11/6/2017.
 *  This class will hit APIs and initiate OM.
 */
public class ServerOperations extends AppCompatActivity{
    private final Utility utility=Utility.INSTANCE;
    private static final String START_API="http://10.100.160.221:8080/initiator/start";
    private static final String STOP_API="http://10.100.160.221:8080/initiator/stop";
    private static final String CLEAN_RESTART_API="http://10.100.160.221:8080/initiator/cleanRestart";
    private static final String KILL_PROCESS="http://10.100.160.221:8080/initiator/killProcess";
    private static final String FREE_MEMORY="http://10.100.160.221:8080/initiator/freeMemory";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        BottomBar bottomBar = (BottomBar) findViewById(R.id.server_operation);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
                int i=0;
                @Override
                public void onTabSelected(@IdRes int tabId){
                    if (i != 0) {
                        if (tabId == R.id.start) {
                            final Intent intentWelcome = new Intent(ServerOperations.this, Start.class);
                            startActivity(intentWelcome);
                        }
                        if (tabId == R.id.stop) {
                            final Intent intentWelcome = new Intent(ServerOperations.this, Stop.class);
                            startActivity(intentWelcome);
                        }
                        if (tabId == R.id.cleanrestart) {
                            final Intent intentWelcome = new Intent(ServerOperations.this, CleanRestart.class);
                            startActivity(intentWelcome);
                        }
                    }
                    i++;
                }
        });
    }
}

