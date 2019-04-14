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

public class MainActivity extends AppCompatActivity {

    //Global variables

    private Button send;
    private EditText message;
    private EditText email;
    private EditText subject;

   private Encryptor myEncryptor;
   private BigInteger EncodedMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void InitView(){
        send= (Button)findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ButtonAction(v);
            }
        });


        message= (EditText)findViewById(R.id.message);
        email= (EditText)findViewById(R.id.email);
        subject= (EditText)findViewById(R.id.subject);
    }



    public void ButtonAction(View view) {
        //Intent startNewActivity= new Intent(this, Encryptor.class);
        //startActivity(startNewActivity);
        InitView();
        Encrypt();
        //sendMail();

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
        myEncryptor= new Encryptor();
        if(message.getText().length()==0) {
            message.setText("Please Enter a Message");
        }else {
            String text= message.getText().toString();
            EncodedMessage= myEncryptor.Encode(text,myEncryptor.getKeySet().getPrivateKey());
            message.setText(EncodedMessage.toString());
            sendMail();
        }
    }

    /*
    public void Decrypt(){
        if(cypherText.getText().equals("Please Enter a Message")){
            clearText.setText("Nothing was encrypted!!!");
        }else{
            String Decoded= myEncryptor.Decode(EncodedMessage,myEncryptor.getKeySet().getPublicKey());
            clearText.setText(Decoded);
        }
        takeAction.setText("Encrypt");
    }
    */


}
