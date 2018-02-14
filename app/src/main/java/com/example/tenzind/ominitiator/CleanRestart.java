package com.example.tenzind.ominitiator;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by TenzinD on 12/9/2017.
 */

public class CleanRestart extends AppCompatActivity {
    private static final String CLEAN_RESTART_API="http://10.100.160.221:8080/initiator/cleanRestart";
    private TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cleanrestart);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        Button cleanrestart = (Button) findViewById(R.id.cleanrestart);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.CANADA);
                    textToSpeech.setSpeechRate(0.8f);
                }
            }
        });
        cleanrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncCallGET().execute(CLEAN_RESTART_API);
                textToSpeech.speak("server is stopping",TextToSpeech.QUEUE_FLUSH,null);
                Toast.makeText(getApplicationContext(),"cleanRestarted",Toast.LENGTH_LONG).show();
            }
        });
    }
}
