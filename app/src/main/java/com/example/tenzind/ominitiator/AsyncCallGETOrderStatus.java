package com.example.tenzind.ominitiator;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
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
 * Created by TenzinD on 12/5/2017.
 */

public class AsyncCallGETOrderStatus extends AsyncTask<String,String,String> {

    @Override
    protected String doInBackground(String... urls) {
        HttpURLConnection httpURLConnection = null;
        BufferedReader bufferedReader=null;
        try {
            URL url=new URL(urls[0]);
            Log.i("URL :",url.toString());
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
           // httpURLConnection.setDoOutput(true);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestProperty ("Authorization", "Basic RGF2aWQgU21pdGg6UGFzc3dvcmQ=");
            httpURLConnection.setRequestProperty("Content-Type", "application/xml");
            httpURLConnection.setRequestProperty("charset", "utf-8");
            httpURLConnection.connect();
            InputStream stream=httpURLConnection.getInputStream();
            bufferedReader=new BufferedReader(new InputStreamReader(stream));
            StringBuffer stringBuffer=new StringBuffer();
            String line="";
            while((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line);
                //Log.i("response API for order status :",stringBuffer.toString());
                String stringFinal=stringBuffer.toString();
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    InputSource is = new InputSource(new StringReader(stringFinal));
                    Document doc = db.parse(is);
                    doc.getDocumentElement().normalize();
                    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
                    NodeList nList = doc.getElementsByTagName("som:OrderQueryResponse");
                    Node nNode = nList.item(0);
                    Element eElement = (Element) nNode;
                    //Log.i("POST status",eElement.getElementsByTagName("som:status").item(0).getTextContent());
                    if(eElement.getElementsByTagName("som:status").item(0)!=null)
                    System.setProperty("status",eElement.getElementsByTagName("som:status").item(0).getTextContent());

                    if(eElement.getElementsByTagName("som:Error").item(0)!=null) {
                        /*NodeList nList2=eElement.getElementsByTagName("som:Error");
                        Node nNode2 = nList2.item(0);
                        Element eElement2 = (Element) nNode2;*/
                        System.out.println("In Error ");
                        System.setProperty("errorStatus", eElement.getElementsByTagName("som:message").item(0).getTextContent());
                        System.setProperty("status", eElement.getElementsByTagName("som:message").item(0).getTextContent());
                    }

                } catch (ParserConfigurationException e) {
                    throw new RuntimeException(e);
                }
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
