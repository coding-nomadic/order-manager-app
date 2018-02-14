package com.example.tenzind.ominitiator;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Created by TenzinD on 12/9/2017.
 */

public class Order extends AppCompatActivity{
    private String TAG = Order.class.getSimpleName();
    String jsonStr=null;
    private final ThreadLocal<ProgressDialog> pDialog = new ThreadLocal<>();
    private ListView lvOrder;
    private static String order = "http://10.100.160.221:9009/omapi/order/execution/3451";
    ArrayList<HashMap<String, String>> orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        setContentView(R.layout.order_listview);
        orderList = new ArrayList<>();
        lvOrder = (ListView) findViewById(R.id.list_order);
        String url =System.getProperty("orderUrl");
        System.out.println("url orderUrl :--------"+url);
        new OrderGetContacts().execute(url);
    }
    /**
     * Async task class to get json by making HTTP call
     */
    private class OrderGetContacts extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.e(TAG,"----------tenzin------------------1");
            pDialog.set(new ProgressDialog(Order.this));
            pDialog.get().setMessage("Please wait...");
            pDialog.get().setCancelable(false);
            pDialog.get().show();
        }
        @Override
        protected String doInBackground(String... arg0) {
            Log.e(TAG,"----------tenzin------------------");
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader=null;
            try {
                URL url = new URL(arg0[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setInstanceFollowRedirects(false);
                httpURLConnection.setRequestProperty("Authorization", "Basic RGF2aWQgU21pdGg6UGFzc3dvcmQ=");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("charset", "utf-8");
                httpURLConnection.connect();
                InputStream stream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer StringBuffer = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    StringBuffer.append(line);
                }
                jsonStr=StringBuffer.toString();
                //return StringBuffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(httpURLConnection!=null)
                {
                    httpURLConnection.disconnect();
                }
                try {
                    if (bufferedReader!=null)
                    {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    //System.out.println("Json Obj:"+jsonObj);
                    //if(jsonObj.getString("orderId")!=null) {
                        String orderId = jsonObj.getString("orderId");
                        String startDtm = jsonObj.getString("startDtm");
                        String lastModifiedDtm = jsonObj.getString("lastModifiedDtm");
                        String state = jsonObj.getString("state");
                        String currentVersion = jsonObj.getString("currentVersion");
                        String customerId = jsonObj.getString("customerId");
                        HashMap<String, String> orders = new HashMap<>();
                        orders.put("orderId", orderId);
                        orders.put("startDtm", startDtm);
                        orders.put("lastModifiedDtm", lastModifiedDtm);
                        orders.put("state", state);
                        orders.put("currentVersion", currentVersion);
                        orders.put("customerId", customerId);
                        System.out.println(orders.toString());
                        orderList.add(orders);
                    /*}
                    else
                        Toast.makeText(getApplicationContext(),jsonObj.toString(),Toast.LENGTH_LONG).show();*/
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    jsonStr,
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.get().isShowing())
                pDialog.get().dismiss();
            ListAdapter adapter = new SimpleAdapter(
                    Order.this, orderList,
                    R.layout.list_order, new String[]{"orderId", "startDtm",
                    "lastModifiedDtm","state","currentVersion","customerId"}, new int[]{R.id.orderId,
                    R.id.startDtm, R.id.lastModifiedDtm,R.id.state,R.id.currentVersion,R.id.customerId});
            Log.e(TAG, "I am here buddy!!");
            lvOrder.setAdapter(adapter);
            Log.e(TAG, "I am here buddy after!!");
        }
    }
}
