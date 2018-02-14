package com.example.tenzind.ominitiator;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.URL;
import java.util.Locale;

/**
 * Created by TenzinD on 12/4/2017.
 */

public class Force extends AppCompatActivity{

    EditText forceEdit;
    private TextToSpeech textToSpeech;
    public static final String FORCE_CLOSE="http://10.100.160.221:9009/omapi/order/forceClose/orderId/";
    private static final String ORDER_STATUS="http://10.100.160.221:9009/omapi/order/status/orderId/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.force);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        Button forceclose = (Button) findViewById(R.id.force);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.CANADA);
                    textToSpeech.setSpeechRate(0.8f);
                }
            }
        });
        forceEdit=(EditText)findViewById(R.id.forceedittext);
        forceclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = forceEdit.getText().toString();
                String newUrl=FORCE_CLOSE+str;

                //To get order status
                System.clearProperty("status");
                System.clearProperty("errorStatus");
                new AsyncCallGETOrderStatus().execute(ORDER_STATUS+str);
                String status=null;
                while(status==null) {
                    status = System.getProperty("status");
                }

                if(status!=null) {
                    if (System.getProperty("errorStatus") == null) {
                        new AsyncCallPOST().execute(newUrl);
                        textToSpeech.speak("Order is aborting", TextToSpeech.QUEUE_FLUSH, null);
                        Toast.makeText(getApplicationContext(), "force closed!!", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(), System.getProperty("errorStatus"), Toast.LENGTH_LONG).show();
                }
                System.clearProperty("status");
                System.clearProperty("errorStatus");
            }
        });
    }
}
