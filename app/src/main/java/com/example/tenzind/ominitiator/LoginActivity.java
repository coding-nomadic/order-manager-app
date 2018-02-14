package com.example.tenzind.ominitiator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Created by TenzinD on 12/4/2017.
 */
public class LoginActivity extends AppCompatActivity{
    EditText user;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        LayoutInflater mInflater = LayoutInflater.from(this);
        View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(mCustomView);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.omfinal));
        user=(EditText) findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        Button button=(Button)findViewById(R.id.click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user.getText().toString().equals("David Smith")||user.getText().toString().equals("Cathy Jones")&&password.getText().toString().equals("Password") )
                {
                    Toast.makeText(getApplicationContext(),"Login successful!!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, MainMenu.class);
                    startActivity(i);
                }else
                {
                    Toast.makeText(getApplicationContext(),"Wrong credentials!!",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(LoginActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        });
        //getActionBar().hide();
    }
}
