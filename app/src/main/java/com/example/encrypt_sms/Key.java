package com.example.encrypt_sms;

import java.math.BigInteger;

public class Key {

    private BigInteger e;
    private BigInteger n;
    private BigInteger d;

    private String Name;

    public String getName(){
        return Name;
    }
    public void setName(String n){
        Name=n;
    }

    public Key(){

    }
}
