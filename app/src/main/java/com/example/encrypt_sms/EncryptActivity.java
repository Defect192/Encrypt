package com.example.encrypt_sms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import java.math.BigInteger;

public class EncryptActivity extends AppCompatActivity {

    //Global variables

    private Button send;
    private EditText message;
    private EditText email;
    private EditText subject;

    private Encryptor encryptMachine;
    private BigInteger EncodedMessage;

    private Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Encrypt a Message");
        send= (Button)findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Encrypt();
                sendMail();
            }
        });


        message= (EditText)findViewById(R.id.message);
        email= (EditText)findViewById(R.id.email);
        subject= (EditText)findViewById(R.id.subject);

        backButton= (Button)findViewById(R.id.back1);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        Key pri= getIntent().getParcelableExtra("private_key");
        Key pub= getIntent().getParcelableExtra("public_key");
        encryptMachine= new Encryptor(false);
    }

    public void sendMail(){
        String emails= email.getText().toString();
        String[] emailList= emails.split(",");

        String subj= subject.getText().toString();

        String mess= message.getText().toString();

        Intent myIntent= new Intent(Intent.ACTION_SEND);
        myIntent.putExtra(Intent.EXTRA_EMAIL, emailList);
        myIntent.putExtra(Intent.EXTRA_SUBJECT, subj);
        myIntent.putExtra(Intent.EXTRA_TEXT, mess);

        //opening email clients
        myIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(myIntent, "Select Client"));
    }

    public void Encrypt(){
        if(message.getText().length()==0) {
            message.setText("Please Enter a Message");
        }
        if(subject.getText().length()==0){
            subject.setText("Please enter a subject");
        }
        if(email.getText().length()==0){
            email.setText("Please enter the recipient(s)");
        }else {
            String text= message.getText().toString();
            EncodedMessage= encryptMachine.Encode(text,encryptMachine.getMyKeys().getPrivateKey()); //Returns a BigInteger
            message.setText(new String(EncodedMessage.toByteArray()));
            sendMail();
        }
    }

}
