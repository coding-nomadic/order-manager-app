package com.example.tenzind.ominitiator;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncCallGET extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... urls) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader=null;
        try {
            URL url=new URL(urls[0]);
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream stream=httpURLConnection.getInputStream();
            bufferedReader=new BufferedReader(new InputStreamReader(stream));
            StringBuffer stringBuffer=new StringBuffer();
            String line="";
            while((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
                Log.e("response API",stringBuffer.toString());
                String stringFinal=stringBuffer.toString();
            }
            return stringBuffer.toString();
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
        return null;
    }
    @Override
    protected void onPostExecute(String str)
    {
        super.onPostExecute(str);
    }
}
