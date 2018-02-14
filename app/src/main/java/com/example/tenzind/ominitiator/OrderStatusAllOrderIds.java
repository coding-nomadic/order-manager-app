package com.example.tenzind.ominitiator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by tenzind on 11/29/2017.
 */
public class OrderStatusAllOrderIds extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final String ORDER_STATUS_ALL="http://10.100.160.221:9009/omapi/order/status/orderId/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderstatusallids);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("OPEN.NOT_STARTED.CREATED");
        categories.add("OPEN.RUNNING.IN_PROGRESS");
        categories.add("OPEN.RUNNING.CANCELLING");
        categories.add("CLOSE.COMPLETED.CANCELLED");
        categories.add("OPEN.RUNNING.FAILED");
        categories.add("OPEN.RUNNING.ERROR");
        categories.add("CLOSE.COMPLETED.DONE");
        categories.add("CLOSE.ABORTED.ABORTED_BY_SERVER");
        categories.add("OPEN.NOT_RUNNING.QUEUED");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        String finalURL=ORDER_STATUS_ALL+item;
        new AsyncCallPOST().execute(finalURL);
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
