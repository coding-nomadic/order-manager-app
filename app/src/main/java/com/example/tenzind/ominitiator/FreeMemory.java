package com.example.tenzind.ominitiator;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
/**
 * Created by TenzinD on 12/9/2017.
 */

public class FreeMemory extends AppCompatActivity {
    private static final String FREE_MEMORY="http://10.100.160.221:8080/initiator/freeMemory";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.freememory);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        Button freememory = (Button) findViewById(R.id.killprocess);
        freememory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncCallGETMemory().execute(FREE_MEMORY);
                String memory = System.getProperty("memory");
                Toast.makeText(getApplicationContext(),memory,Toast.LENGTH_LONG).show();
            }
        });
    }
}
