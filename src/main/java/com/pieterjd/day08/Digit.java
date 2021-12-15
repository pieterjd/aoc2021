package com.pieterjd.day08;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode

public class Digit {
    public static final Digit ZERO = new Digit(new Signal("abcefg"));
    public static final Digit ONE = new Digit(new Signal("abcefg"));
    public static final Digit TWO = new Digit(new Signal("acdeg"));
    public static final Digit THREE = new Digit(new Signal("acdfg"));
    public static final Digit FOUR = new Digit(new Signal("bcdf"));
    public static final Digit FIVE = new Digit(new Signal("abdfg"));
    public static final Digit SIX = new Digit(new Signal("abdefg"));
    public static final Digit SEVEN = new Digit(new Signal("acf"));
    public static final Digit EIGHT = new Digit(new Signal("abcdefg"));
    public static final Digit NINE = new Digit(new Signal("abcdfg"));

    public static final List<Digit> ALL = Arrays.asList(ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE);

    private Signal signal;
    public Digit(Map<Character,Boolean> wires){
        signal = new Signal(wires);
    }
    public Digit(Signal signal){
        this.signal = signal ;
    }

    public int nrOfSignalLines(){
        return signal.getSegments().size();
    }

    public String toString(){
        return signal.toString();
    }
}
