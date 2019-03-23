package com.example.encrypt_sms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {

    //Global variables

    private Button takeAction;
    private TextInputEditText typed;
    private ScrollView messageView;

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
        takeAction= (Button)findViewById(R.id.send);
        typed= (TextInputEditText)findViewById(R.id.textInputEditText);
        messageView= (ScrollView)findViewById(R.id.scrollView2);
    }

    public void ButtonAction(View view) {
        //Intent startNewActivity= new Intent(this, encrypt.class);
        //startActivity(startNewActivity);
        InitView();

    }

    /*
    public void Encrypt(){
        myEncryptor= new Encryptor();
        if(typed.getText().length()==0) {
            cypherText.setText("Please Enter a Message");
            clearText.setText("");
        }else {
            String text= typed.getText().toString();
            EncodedMessage= myEncryptor.Encode(text,myEncryptor.getKeySet().getPrivateKey());
            cypherText.setText(EncodedMessage.toString());
        }
        takeAction.setText("Decrypt");
    }

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
