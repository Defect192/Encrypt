package com.example.encrypt_sms;

import java.math.BigInteger;

public class Encryptor {

    private KeySet myKeys;

    public Encryptor(){
        myKeys = new KeySet();
    }

    public KeySet getKeySet(){
        return myKeys;
    }


    public BigInteger Encode(String Message, Key privateKey) {
        BigInteger N= privateKey.getN();
        BigInteger D= privateKey.getDorE();
        byte[] ByteMessage = Message.getBytes();
        BigInteger IntMessage = new BigInteger(ByteMessage);
        BigInteger EncodedMessage = ModularExponent(IntMessage,D,N);
        return EncodedMessage;
    }

    public String Decode(BigInteger Encoded, Key publicKey) {
        BigInteger N= publicKey.getN();
        BigInteger E= publicKey.getDorE();
        BigInteger DecodedMessage = ModularExponent(Encoded,E,N);
        byte[] decodedByte= DecodedMessage.toByteArray();
        String DecodedString= new String(decodedByte);
        return DecodedString;
    }

    private BigInteger ModularExponent(BigInteger a, BigInteger b, BigInteger n) {
        int c=0;
        BigInteger d= new BigInteger("1");
        String Binaryb = b.toString(2);
        Binaryb = ReverseBits(Binaryb);
        for(int i= Binaryb.length()-1; i>-1; i--) {
            c = c*2;
            d = d.multiply(d);
            d = d.mod(n);
            if(Binaryb.charAt(i)=='1') {
                c = c+1;
                d = d.multiply(a);
                d = d.mod(n);
            }
        }
        return d;
    }

    private String ReverseBits(String BiNum) {
        String ReversedNum= "";
        for(int i=BiNum.length()-1; i>-1; i--) {
            ReversedNum+=BiNum.charAt(i);
        }
        return ReversedNum;
    }

}
