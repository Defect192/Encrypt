package com.example.encrypt_sms;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Encryptor{

    private KeySet myKeys; //my public and private keys
    private Key[] friendKeys; //friends' public keys

    public Encryptor(boolean createKey){ //true: create keys, false: get keys from file
        myKeys = new KeySet();
        if(createKey) {
            Dump();
        }else try {
            File root = new File(Environment.getExternalStorageDirectory(),"EncryptFolder");
            File myKeySafe= new File(root,"myKeySafe.txt");
            BufferedReader Reader = new BufferedReader(new FileReader(myKeySafe));
            String line = Reader.readLine();
            while (line != null) {
                String[] keyParts = line.split(",");
                //DE, N, Name
                Key k = new Key(new BigInteger(keyParts[0]), new BigInteger(keyParts[1]), keyParts[2]);
                if (keyParts[2].equals("Private")) {
                    myKeys.setPrivateKey(k);
                } else {
                    myKeys.setPublicKey(k);
                }
                line = Reader.readLine();
            }
            Log.i("File Reading","Success!!");
        } catch (IOException e) {
            Log.i("File Reading", "File Reading Failed");
            e.printStackTrace();
        }
    }

    public KeySet getMyKeys(){
        return myKeys;
    }

    public void setMyKeys(KeySet k){
        myKeys= k;
        Dump();
    }


    //String -> BigInteger
    public BigInteger Encode(String Message, Key privateKey) {
        BigInteger N= privateKey.getN();
        BigInteger D= privateKey.getDorE();
        byte[] ByteMessage = Message.getBytes();
        BigInteger IntMessage = new BigInteger(ByteMessage);
        BigInteger EncodedMessage = ModularExponent(IntMessage,D,N);
        return EncodedMessage;
    }

    //BigInteger -> String
    public String Decode(BigInteger Encoded, Key publicKey) {
        BigInteger N= publicKey.getN();
        BigInteger E= publicKey.getDorE();
        BigInteger DecodedMessage = ModularExponent(Encoded,E,N);
        byte[] decodedByte= DecodedMessage.toByteArray();
        String DecodedString= new String(decodedByte);
        return DecodedString;
    }

    //String -> String
    public String Decode(String Encoded, Key publicKey) {
        BigInteger N= publicKey.getN();
        BigInteger E= publicKey.getDorE();
        BigInteger Mess= new BigInteger(Encoded.getBytes());
        BigInteger DecodedMessage = ModularExponent(Mess,E,N);
        byte[] decodedByte= DecodedMessage.toByteArray();
        String DecodedString= new String(decodedByte);
        return DecodedString;
    }



    public void Dump(){ //DE, N, Name
        File root = new File(Environment.getExternalStorageDirectory(),"EncryptFolder");
        if(!root.exists()){
            root.mkdir();
        }
        File myKeySafe= new File(root,"myKeySafe.txt");
        Key pri= myKeys.getPrivateKey();
        Key pub= myKeys.getPublicKey();
        try {

            /*
            FileWriter writer= new FileWriter(myKeySafe);
            writer.append(new String(pri.getDorE().toByteArray()));
            writer.append(new String(pri.getN().toByteArray()));
            writer.append(pri.getName());
            writer.append("\n");
            writer.append(new String(pub.getDorE().toByteArray())+","+new String(pub.getN().toByteArray())+","+pub.getName());
            writer.flush();
            writer.close();
            */


            FileWriter writer= new FileWriter(myKeySafe);
            writer.append(pri.getDorE()+","+pri.getN()+","+pri.getName());
            writer.append("\n");
            writer.append(pub.getDorE()+","+pub.getN()+","+pub.getName());
            writer.flush();
            writer.close();


            Log.i("File Creation","Success!!");
            Log.i("path",myKeySafe.getAbsolutePath());
        } catch (IOException e) {
            Log.i("File Creation", "Failed");
            e.printStackTrace();
        }

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
