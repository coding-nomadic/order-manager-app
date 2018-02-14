package com.example.tenzind.ominitiator;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
/**
 * Created by tenzind on 11/29/2017.
 */

public class AsyncCallPOST extends AsyncTask<String,String,String> {
    @Override
    protected String doInBackground(String... urls) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader=null;
        try {
            URL url=new URL(urls[0]);
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty ("Authorization", "Basic RGF2aWQgU21pdGg6UGFzc3dvcmQ=");
            httpURLConnection.setRequestProperty("Content-Type", "text/plain");
            httpURLConnection.setRequestProperty("charset", "utf-8");
            httpURLConnection.connect();
            InputStream stream=httpURLConnection.getInputStream();
            bufferedReader=new BufferedReader(new InputStreamReader(stream));
            StringBuffer StringBuffer=new StringBuffer();
            String line="";
            while((line=bufferedReader.readLine())!=null){
                StringBuffer.append(line);
            }
            return StringBuffer.toString();
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

    private Document parseXmlFile(String in) {

        return null;
    }
}
