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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class KeyActivity extends AppCompatActivity {

    private Button backbutton;
    private Button addbutton;
    private Button deletebutton;
    private EditText enterkeyname;

    private LinearLayout List;

    private Encryptor encryptMachine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enterkeyname= (EditText)findViewById(R.id.enterKeyName);

        List= (LinearLayout)findViewById(R.id.ListLayout);

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

        encryptMachine= new Encryptor(false);
        initKeys();
    }

    public void addKey(View v){
        if(enterkeyname.getText().length()!=0 && !enterkeyname.getText().toString().equals("Please enter a name")) {
            Key added = new Key();
            added.setName(enterkeyname.getText().toString());
            encryptMachine.addKey(added);
            displayKeys();
        }else{
            enterkeyname.setText("Please enter a name");
        }
    }

    public void eraseKey(View v){
        if(enterkeyname.getText().length()!=0 && !enterkeyname.getText().toString().equals("Please enter a name")) {
            String name = enterkeyname.getText().toString();
            ArrayList<Key> myKeys = encryptMachine.getKeyChain();
            for (int i = 0; i < myKeys.size(); i++) {
                if (myKeys.get(i).getName().equals(name)) {
                    myKeys.remove(i);
                }
            }
            displayKeys();
        }else{
            enterkeyname.setText("Please enter a name");
        }
    }

    public void displayKeys(){
        List.removeAllViews();
        initKeys();
        ArrayList<Key> myKeys = encryptMachine.getKeyChain();
        for(int i=0; i<myKeys.size(); i++){
            TextView tv= new TextView(this);
            tv.setText(myKeys.get(i).getName());
            tv.setTextSize(30);
            List.addView(tv);
        }
    }

    public void initKeys(){
        TextView pri= new TextView(getBaseContext());
        pri.setText(encryptMachine.getMyKeys().getPrivateKey().getName());
        pri.setTextSize(30);
        List.addView(pri);
        TextView pub= new TextView(this);
        pub.setText(encryptMachine.getMyKeys().getPublicKey().getName());
        pub.setTextSize(30);
        List.addView(pub);
    }
}
