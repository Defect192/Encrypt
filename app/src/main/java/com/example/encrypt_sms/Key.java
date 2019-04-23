package com.example.encrypt_sms;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigInteger;

public class Key{

    private BigInteger DorE;
    private BigInteger N;
    private String Name;

    public Key(){
        DorE= new BigInteger("0");
        N= new BigInteger("0");
        Name= "Blank Key";
    }

    public Key(BigInteger de, BigInteger n, String label){
        DorE=de;
        N=n;
        Name=label;
    }

    public Key(String de, String n, String label){
        DorE= new BigInteger(de);
        N= new BigInteger(n);
        Name=label;
    }

    public String getName(){
        return Name;
    }
    public void setName(String n){
        Name=n;
    }

    public BigInteger getDorE(){
        return DorE;
    }

    public BigInteger getN(){
        return N;
    }
}
