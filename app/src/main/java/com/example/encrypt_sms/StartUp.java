package com.example.encrypt_sms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;

public class StartUp extends AppCompatActivity {

    private Button GoToEncrypt;
    private Button GoToDecrypt;
    private Button GoToKeys;

    private Encryptor myEncryptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        InitView();
    }


    public void InitView(){

        GoToEncrypt= (Button)findViewById(R.id.EncryptView);
        GoToEncrypt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(StartUp.this, EncryptActivity.class));
            }
        });


        GoToDecrypt= (Button)findViewById(R.id.DecryptView);
        GoToDecrypt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(StartUp.this, DecryptActivity.class));
            }
        });

        GoToKeys= (Button)findViewById(R.id.KeyView);
        GoToKeys.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(StartUp.this, KeyActivity.class));
            }
        });

        myEncryptor= new Encryptor(true);
    }

}
