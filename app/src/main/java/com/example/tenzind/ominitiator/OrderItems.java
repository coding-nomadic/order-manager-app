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
public class OrderItems extends AppCompatActivity{
    private String TAG = OrderItems.class.getSimpleName();
    String jsonStr=null;
    private final ThreadLocal<ProgressDialog> pDialog = new ThreadLocal<>();
    private ListView lv;
    // URL to get contacts JSON
    private static String orderItemsUrl = "http://10.100.160.221:9009/omapi/order/execution/1101";
    ArrayList<HashMap<String, String>> contactList;
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
        setContentView(R.layout.orderitems_listview);
        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list_orderitems);
        String url =System.getProperty("orderItemsUrl");
        System.out.println("url order items :--------"+url);
        new GetContacts().execute(url);
    }
    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.set(new ProgressDialog(OrderItems.this));
            pDialog.get().setMessage("Please wait...");
            pDialog.get().setCancelable(false);
            pDialog.get().show();
        }
        @Override
        protected String doInBackground(String... arg0) {
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
                    // Getting JSON Array node
                    JSONArray orderItem = jsonObj.getJSONArray("orderItem");
                    // looping through All Contacts
                    for (int i = 0; i < orderItem.length(); i++) {
                        JSONObject c = orderItem.getJSONObject(i);
                        String orderItemId = c.getString("orderItemId");
                        String displayName = c.getString("displayName");
                        String catalogName = c.getString("catalogName");
                        String orderItemState = c.getString("orderItemState");
                        HashMap<String, String> orderItems = new HashMap<>();
                        // adding each child node to HashMap key => value
                        orderItems.put("orderItemId", orderItemId);
                        orderItems.put("displayName", displayName);
                        orderItems.put("catalogName", catalogName);
                        orderItems.put("orderItemState", orderItemState);
                        // adding contact to contact list
                        contactList.add(orderItems);
                    }
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
                    OrderItems.this, contactList,
                    R.layout.list_orderitem, new String[]{"orderItemId", "displayName",
                    "catalogName","orderItemState"}, new int[]{R.id.orderItemId,
                    R.id.displayName, R.id.catalogName,R.id.orderItemState});
            lv.setAdapter(adapter);
        }
    }
}
