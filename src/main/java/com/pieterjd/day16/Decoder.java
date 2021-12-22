package com.pieterjd.day16;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Decoder {
    private String hex;
    private String bin;

    public Decoder(String hex) {
       this.hex = hex;
    }



    public String hexToBin(){
        bin=new BigInteger(hex,16).toString(2);
        return bin;
    }

    public int getPacketVersion(){
        return new BigInteger(bin.substring(0,3),2).intValue();
    }
    public int getPacketTypeId(){
        return new BigInteger(bin.substring(3,6),2).intValue();
    }

    public int getLiteral(){
        return getLiteral(bin.substring(6));
    }
    public int getLiteral(String remainderStr){
        String binNumber = "";
        int startIndex=0;

        while(startIndex+5<remainderStr.length()){
            String fiveBits=remainderStr.substring(startIndex, Math.min(remainderStr.length(),startIndex+5));
            binNumber+= fiveBits.substring(1);
            startIndex+=5;


        }

        return new BigInteger(binNumber,2).intValue();
    }

    public int getLengthTypeId(){
        return Integer.parseInt(bin.substring(6,7));
    }
}
