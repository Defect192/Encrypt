package com.example.encrypt_sms;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KeyActivity extends AppCompatActivity {

    private Button backbutton;
    private Button addbutton;
    private Button deletebutton;
    private EditText enterkeyname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enterkeyname= (EditText)findViewById(R.id.enterKeyName);

        backbutton= (Button)findViewById(R.id.back3);
        backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        addbutton= (Button)findViewById(R.id.add);
        addbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addKey(v);
            }
        });

        deletebutton= (Button)findViewById(R.id.delete);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                eraseKey(v);
            }
        });
    }

    public void addKey(View v){

    }

    public void eraseKey(View v){

    }


}
