package com.example.encrypt_sms;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class Encryptor{

    private KeySet myKeys; //my public and private keys
    private ArrayList<Key> friendKeys; //friends' public keys

    public Encryptor(boolean createKey){ //true: create keys, false: get keys from file
        friendKeys= new ArrayList<Key>();
        myKeys = new KeySet();
        if(createKey) {
            Dump();
        }else try {
            File root = new File(Environment.getExternalStorageDirectory(),"EncryptFolder");
            File N= new File(root,"N.txt");
            File D= new File(root,"D.txt");
            File E= new File(root,"E.txt");

            Scanner input = new Scanner(N);
            String num= "";
            while(input.hasNextLine()) {
                num+=input.next();
            }
            BigInteger n= new BigInteger(num);

            Scanner input2 = new Scanner(D);
            String num2= "";
            while(input2.hasNextLine()) {
                num2+=input2.next();
            }
            BigInteger d= new BigInteger(num2);

            Scanner input3 = new Scanner(E);
            String num3= "";
            while(input3.hasNextLine()) {
                num3+=input3.next();
            }
            BigInteger e= new BigInteger(num3);


            Key pri= new Key(d,n,"Private"); //D, N, Name
            Key pub= new Key(e,n,"Public");  //E, N, Name

            myKeys= new KeySet(pri,pub);  //private, public

        } catch (IOException e) {
            Log.i("File Reading", "File Reading Failed");
            e.printStackTrace();
        }
    }

    public void addKey(Key k){
        friendKeys.add(k);
    }

    public void deleteKey(Key k){
        friendKeys.remove(k);
    }

    public ArrayList<Key> getKeyChain(){
        return friendKeys;
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


    public void Dump(){ //DE, N, Name
        File root = new File(Environment.getExternalStorageDirectory(),"EncryptFolder");
        if(!root.exists()){
            root.mkdir();
        }
        File D= new File(root,"D.txt");  //Comes from private key
        File N= new File(root,"N.txt");  //Comes from both keys
        File E= new File(root,"E.txt");  //Comes from public key
        Key pri= myKeys.getPrivateKey();
        Key pub= myKeys.getPublicKey();
        try {

            FileWriter writeD= new FileWriter(D);
            writeD.append(pri.getDorE().toString());
            writeD.close();

            FileWriter writeE= new FileWriter(E);
            writeE.append(pub.getDorE().toString());
            writeE.close();

            FileWriter writeN= new FileWriter(N);
            writeN.append(pri.getN().toString());
            writeN.close();

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
