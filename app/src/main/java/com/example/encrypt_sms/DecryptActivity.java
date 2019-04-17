package com.example.encrypt_sms;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;

public class DecryptActivity extends AppCompatActivity {

    private Button Decrypt;
    private Button Back;
    private EditText EnterKey;
    private EditText EnterMessage;
    private TextView DisplayMessage;

    private Encryptor decryptMachine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Decrypt = (Button)findViewById(R.id.DecryptButton);
        Back = (Button)findViewById(R.id.back2);
        EnterKey = (EditText)findViewById(R.id.EnterKey);
        EnterMessage = (EditText)findViewById(R.id.PasteMessage);
        DisplayMessage = (TextView)findViewById(R.id.Message);


        Decrypt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                decryptMessage(v);
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        decryptMachine= new Encryptor(false);


    }

    public void decryptMessage(View v){
        if(EnterMessage.getText().length()!=0){
            String Decoded= decryptMachine.Decode(new BigInteger(EnterMessage.getText().toString().getBytes()),decryptMachine.getMyKeys().getPublicKey());
            DisplayMessage.setText(Decoded);
        }

    }


}
