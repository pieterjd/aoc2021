package com.pieterjd.day10;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Getter
@Builder
public class ParseInformation {
    private boolean success, stackEmpty;
    private Character expected, found;
    private int position;
    private String completion;

    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("Success?: %s; ", Boolean.valueOf(success).toString()));
        if(!isSuccess()){
            sb.append(String.format("expected %s but found %s instead at position %d",expected, found, position));
        }
        return sb.toString();
    }

    public boolean isIncomplete(){
        return isSuccess() && isStackEmpty();
    }
    public boolean isCorrupted(){
        return !isSuccess() && expected != null;
    }

    public long completionScore(){
        Map<Character,Integer> points = new HashMap<>();
        points.put(')',1);
        points.put(']',2);
        points.put('}',3);
        points.put('>',4);
        String completion = getCompletion();
        long sum = 0;

        for (int i = 0; i < completion.length(); i++) {
            int score = points.get(completion.charAt(i));
            sum = sum*5 + score;
        }
        return sum;
    }
}
