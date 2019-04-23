package com.example.encrypt_sms;

import java.math.BigInteger;
import java.util.Random;

public class KeySet {

    private static BigInteger P;
    private static BigInteger Q;
    private static BigInteger N;
    private static BigInteger On;
    private static BigInteger E;
    private static BigInteger D;

    private Key Public;
    private Key Private;

    public KeySet(){
        makeP();
        makeQ();
        makeN();
        makeOn();
        makeE();
        makeD();
        Public = new Key(E,N,"Public Key");
        Private= new Key(D,N,"Private Key");
    }

    public KeySet(Key pri, Key pub){
        Private=pri;
        Public=pub;
    }

    public Key getPrivateKey(){
        return Private;
    }

    public Key getPublicKey(){
        return Public;
    }

    public void setPublicKey(Key k){
        Public= k;
    }

    public void setPrivateKey(Key k){
        Private= k;
    }



    public void clearKeys() {
        P = new BigInteger("0");
        Q = new BigInteger("0");
        N = new BigInteger("0");
        On = new BigInteger("0");
        E = new BigInteger("0");
        D = new BigInteger("0");
    }

    private BigInteger Euclid(BigInteger a, BigInteger b) {
        if(b.equals(new BigInteger("0"))) {
            return a;
        }else {
            return Euclid(b, a.mod(b));
        }
    }

    private void makeP() {
        Random rand= new Random();
        P = BigInteger.probablePrime(1024, rand);
    }

    private void makeQ() {
        Random rand= new Random();
        Q = BigInteger.probablePrime(1024, rand);
        if(Q.equals(P)) {
            makeQ();
        }
    }

    private void makeN() {
        N= P.multiply(Q);
    }

    private void makeOn() {
        BigInteger pM1 = P.subtract(new BigInteger("1"));
        BigInteger qM1 = Q.subtract(new BigInteger("1"));
        On = pM1.multiply(qM1);
    }

    private void makeE() {
        Random rand= new Random();
        E = BigInteger.probablePrime(16, rand);
        if(!Euclid(E,On).equals(new BigInteger("1"))) {
            makeE();
        }
    }

    private void makeD() {
        D = E.modInverse(On);
    }


}
