package com.example.tenzind.ominitiator;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import java.util.Locale;
/**
 * Main class.
 */
public class MainActivity extends AppCompatActivity {
   private TextToSpeech textToSpeech;
   private Button welcomeButton;
    private final Utility utility=Utility.INSTANCE;
    private static final String TAG="MainActivity";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        /*welcomeButton = (Button) findViewById(R.id.welcomeButton);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.CANADA);
                    textToSpeech.setSpeechRate(0.8f);
                }
            }
        });
        welcomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //textToSpeech.speak("welcome to OM control",TextToSpeech.QUEUE_FLUSH,null);
                    final Intent intentWelcome=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intentWelcome);
                    String token = FirebaseInstanceId.getInstance().getToken();
                    Log.d(TAG, "Token ID ---->" + token);
                    //Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });*/
        try {
            //textToSpeech.speak("welcome to OM control",TextToSpeech.QUEUE_FLUSH,null);
            final Intent intentWelcome=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intentWelcome);
            String token = FirebaseInstanceId.getInstance().getToken();
            Log.d(TAG, "Token ID ---->" + token);
            //Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
