package com.example.tenzind.ominitiator;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Created by ninadc on 12/11/2017.
 */

public class Orders extends AppCompatActivity {
    private String TAG = OrderItems.class.getSimpleName();
    String jsonStr=null;
    private final ThreadLocal<ProgressDialog> pDialog = new ThreadLocal<>();
    private ListView lv;
    // URL to get contacts JSON
    private static String tasks = "http://10.100.160.221:9009/omapi/order/execution/3451";
    ArrayList<HashMap<String, String>> taskList;
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
        setContentView(R.layout.task_listview);
        taskList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list_orders);
        String url =System.getProperty("tasksUrl");
        System.out.println("url tasks:--------"+url);
        //new OrdrDetails().execute();
    }
    /**
     * Async task class to get json by making HTTP call
     */
    private class OrdrDetails extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog.set(new ProgressDialog(Orders.this));
            pDialog.get().setMessage("Please wait...");
            pDialog.get().setCancelable(false);
            pDialog.get().show();
        }
        @Override
        protected String doInBackground(String... arg0) {

            /*HttpURLConnection httpURLConnection = null;
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
            //Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                JSONArray taskDetails = null;
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);
                    // Getting JSON Array node
                    JSONArray orderItem = jsonObj.getJSONArray("orderItem");
                    JSONArray processDetails = jsonObj.getJSONArray("processDetails");
                    for (int i = 0; i < processDetails.length(); i++) {
                        final JSONArray subProcessDetails = processDetails.getJSONObject(i)
                                .getJSONArray("subProcessDetails");
                        for (int j = 0; j < subProcessDetails.length(); j++) {
                            taskDetails = subProcessDetails.getJSONObject(j).getJSONArray("taskDetails");
                            for(int k=0;k<taskDetails.length();k++)
                            {
                                System.out.println(taskDetails.getJSONObject(k).getJSONObject("id").get("name"));
                                HashMap<String, String> tasks = new HashMap<>();
                                tasks.put("taskname", taskDetails.getJSONObject(k).getJSONObject("id").get("name").toString());
                                tasks.put("createdDtm", taskDetails.getJSONObject(k).getString("createdDtm"));
                                tasks.put("modifiedDtm", taskDetails.getJSONObject(k).getString("modifiedDtm"));
                                tasks.put("expectedDuration", taskDetails.getJSONObject(k).getString("expectedDuration"));
                                tasks.put("taskState", taskDetails.getJSONObject(k).getString("taskState"));
                                tasks.put("taskType", taskDetails.getJSONObject(k).getString("taskType"));
                                tasks.put("version", taskDetails.getJSONObject(k).getString("version"));
                                tasks.put("currInstanceId", taskDetails.getJSONObject(k).getString("currInstanceId"));
                                tasks.put("slaBreached", taskDetails.getJSONObject(k).getString("slaBreached"));
                                //System.out.println(orders.toString());
                                taskList.add(tasks);
                            }
                        }
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
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

            }*/
            Connection connection = null;
            try {

                // Load the Oracle JDBC driver

                String driverName = "oracle.jdbc.driver.OracleDriver";

                Class.forName(driverName);


                // Create a connection to the database

                String serverName = "punvm-oracle11g.sigmasys.net";

                String serverPort = "1521";

                String sid = "SIDEV11G";

                String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;

                String username = "sesolnom3";

                String password = "sesolnom3";

                connection = DriverManager.getConnection(url, username, password);



                System.out.println("Successfully Connected to the database!");


            } catch (ClassNotFoundException e) {

                System.out.println("Could not find the database driver " + e.getMessage());
            } catch (SQLException e) {

                System.out.println("Could not connect to the database " + e.getMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.get().isShowing())
                pDialog.get().dismiss();
            ListAdapter adapter = new SimpleAdapter(
                    Orders.this, taskList,
                    R.layout.list_task, new String[]{"taskname", "createdDtm",
                    "modifiedDtm","expectedDuration","taskState","taskType","version","currInstanceId","slaBreached"}, new int[]{R.id.taskname,
                    R.id.createdDtm, R.id.modifiedDtm,R.id.expectedDuration,R.id.taskState,R.id.taskType,R.id.version,R.id.currInstanceId,R.id.slaBreached});
            lv.setAdapter(adapter);
        }
    }
}
