package com.example.encrypt_sms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Global variables
    private TextView clearText;
    private TextView cypherText;
    private Button takeAction;
    private TextInputEditText typed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
        clearText= (TextView)findViewById(R.id.textView);
        cypherText= (TextView)findViewById(R.id.textView2);
        takeAction= (Button)findViewById(R.id.button2);
        typed= (TextInputEditText)findViewById(R.id.textInputEditText);
    }

    public void ButtonAction(View view) {
        //Intent startNewActivity= new Intent(this, encrypt.class);
        //startActivity(startNewActivity);
        InitView();
        if(takeAction.getText().equals("Encrypt")){
            Encrypt();
        }else if(takeAction.getText().equals("Decrypt")){
            Decrypt();
        }
    }

    public void Encrypt(){
        //InitView();
        if(typed.getText().length()==0) {
            cypherText.setText("Please Enter a Message");
            clearText.setText("");
        }else{
            cypherText.setText(typed.getText());
        }
        takeAction.setText("Decrypt");
    }

    public void Decrypt(){
        if(cypherText.getText().equals("Please Enter a Message")){
            clearText.setText("Nothing was encrypted!!!");
        }else{
            clearText.setText(cypherText.getText());
        }
        takeAction.setText("Encrypt");
    }



}
