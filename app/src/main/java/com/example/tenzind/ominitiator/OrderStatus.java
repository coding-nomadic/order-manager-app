package com.example.tenzind.ominitiator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by tenzind on 11/29/2017.
 */

public class OrderStatus extends AppCompatActivity {

    private static final String ORDER_STATUS="http://10.100.160.221:9009/omapi/order/status/orderId/";
    private static final String TAG="OrderStatus";
    EditText orderstatusEditText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderstatus);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        Button orderstatusId = (Button) findViewById(R.id.orderstatusid);
        orderstatusEditText=(EditText)findViewById(R.id.orderstatusEditText);
        orderstatusId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = orderstatusEditText.getText().toString();
                String newUrl=ORDER_STATUS+str;
                new AsyncCallGETOrderStatus().execute(newUrl);
                String status=null;
                while(status==null) {
                    status = System.getProperty("status");
                }
                Log.d(TAG,"order status :"+status);
                Toast.makeText(getApplicationContext(),status,Toast.LENGTH_LONG).show();
                System.clearProperty("status");
            }
        });
    }
}
