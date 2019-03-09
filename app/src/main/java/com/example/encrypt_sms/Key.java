package com.example.encrypt_sms;

import java.math.BigInteger;

public class Key {

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

    public String getName(){
        return Name;
    }

    public BigInteger getDorE(){
        return DorE;
    }

    public BigInteger getN(){
        return N;
    }






}
