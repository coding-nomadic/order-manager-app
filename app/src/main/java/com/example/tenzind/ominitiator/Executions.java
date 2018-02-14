package com.example.tenzind.ominitiator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

/**
 * Created by tenzind on 11/29/2017.
 */
public class Executions extends AppCompatActivity{
    EditText cancelEdit;
    EditText forceEdit;
    public static final String CANCEL="http://10.100.160.221:9009/omapi/order/cancel/orderId/";
    public static final String FORCE_CLOSE="http://10.100.160.221:9009/omapi/order/forceClose/orderId/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.executiondetails);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        BottomBar bottomBar = (BottomBar) findViewById(R.id.execution_tab);
        bottomBar .setOnTabSelectListener(new OnTabSelectListener() {
                int i=0;
            @Override
            public void onTabSelected(@IdRes int tabId){
                if(i!=0) {
                    if (tabId == R.id.orderdetails_tab) {
                        final Intent intentWelcome = new Intent(Executions.this, OrderDetails.class);
                        startActivity(intentWelcome);
                    }
                    if (tabId == R.id.taskdetails_tab) {
                        final Intent intentWelcome = new Intent(Executions.this, TaskDetails.class);
                        startActivity(intentWelcome);
                    }
                    if (tabId == R.id.orderitemdetails_tab) {
                        final Intent intentWelcome = new Intent(Executions.this, OrderItemDetails.class);
                        startActivity(intentWelcome);
                    }
                    if (tabId == R.id.assigntasks_tab) {
                        final Intent intentWelcome = new Intent(Executions.this, AssignTask.class);
                        startActivity(intentWelcome);
                    }
                }
                i++;
            }
        });
    }
}
