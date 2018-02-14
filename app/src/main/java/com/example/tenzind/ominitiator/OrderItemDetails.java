package com.example.tenzind.ominitiator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by TenzinD on 12/9/2017.
 */

public class OrderItemDetails extends AppCompatActivity {

    private static String orderItemsUrl = "http://10.100.160.221:9009/omapi/order/execution/";
    private EditText orderitemsedit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderitems);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        Button orderItems = (Button) findViewById(R.id.orderitems);
        orderitemsedit=(EditText)findViewById(R.id.orderitemsedit);
        orderItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = orderitemsedit.getText().toString();
                String newUrl=orderItemsUrl+str;
                System.setProperty("orderItemsUrl",newUrl);
                final Intent intentWelcome=new Intent(OrderItemDetails.this,OrderItems.class);
                startActivity(intentWelcome);
                //Toast.makeText(getApplicationContext(),"orderItems!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
