package com.pieterjd.day08;

import lombok.Getter;

import java.util.*;

public class Counter {
    @Getter
    private Map<Digit, Integer> counts= new HashMap<>();

    private Map<Integer, Digit> nrLinesToDigitMap = new HashMap<>();

    public Counter() {
        nrLinesToDigitMap.put(2, Digit.ONE);
        nrLinesToDigitMap.put(3, Digit.SEVEN);
        nrLinesToDigitMap.put(4, Digit.FOUR);
        nrLinesToDigitMap.put(7, Digit.EIGHT);
    }

    public int getSummedCount(){
        return counts.values().stream()
                .mapToInt(i->i)
                .sum();
    }

    public void addToCounts(Digit d){
        if(nrLinesToDigitMap.containsKey(d.nrOfSignalLines())){
            //System.out.println(d);
            counts.merge(d,1,(n1,n2)->n1+n2);
        }
    }
}
