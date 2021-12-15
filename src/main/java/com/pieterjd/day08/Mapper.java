package com.pieterjd.day08;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Mapper {
    //maps string of 7 chars to digit
    @Getter
    private Map<String, Integer> stringToInt = new HashMap<>();
    //inverse mapping of previous one
    @Getter
    private Map<Integer, String> intToString = new HashMap<>();
    //maps nr of segments to digit
    private Map<Integer, Integer> uniqueLengthMapping = new HashMap<>();
    private Map<Integer, Integer> nrSegmentsToInt = new HashMap<>();

    public Mapper() {
        uniqueLengthMapping();
    }


    public void addStringToInt(String s, Integer i){
        stringToInt.put(s,i);
        intToString.put(i,s);
    }
    //returns trus if s1 in contained in s2
    private boolean isContainedIn(String s1, String s2) {
        return IntStream.range(0, s1.length())
                .mapToObj(i -> s1.charAt(i))
                .allMatch(c -> s2.indexOf(c) != -1);

    }

    private void uniqueLengthMapping() {
        uniqueLengthMapping.put(2, 1);
        uniqueLengthMapping.put(4, 4);
        uniqueLengthMapping.put(3, 7);
        uniqueLengthMapping.put(7, 8);
    }


    //digits 0, 6 and 9 have 6 segments
    // * 9 is only digit that contains both 4 and 7
    //* of 0 and 6, only 0 contains 7
    private void mapNineAndZero(List<Signal> signals) {
        String seven = intToString.get(7);
        String four = intToString.get(4);
        //get signals with length 6
        List<String> length6 = signals.stream()
                .map(s->s.toString())
                .filter(s -> s.length() == 6)
                .collect(Collectors.toList());
       String nine = length6.stream().filter(s->isContainedIn(four,s) &&isContainedIn(seven,s) )
               .findFirst().get();
       addStringToInt(nine, 9);
       length6.remove(nine); // remove digit we just mapped

        String zero = length6.stream().filter(s -> isContainedIn(seven, s))
                .findFirst().get();
        addStringToInt(zero, 0);
        length6.remove(zero);

        //last element in length6 is 6
        String six = length6.get(0);;
        addStringToInt(six,6);

    }

    //digits 2,3 and 5 have 5 segments:
    // * only 3 contains 7
    // * 9 only contains 5

    private void mapTwoThreeAndFive(List<Signal> signals){
        String seven = intToString.get(7);
        List<String> length5 = signals.stream()
                .map(s->s.toString())
                .filter(s -> s.length() == 5)
                .collect(Collectors.toList());
        String three = length5.stream().filter(l5 -> isContainedIn(seven,l5 ))
                .findFirst().get();
        length5.remove(three);
        addStringToInt(three, 3);

        String nine = intToString.get(9);
        String five = length5.stream().filter(l5 -> isContainedIn(l5, nine))
                .findFirst().get();
        length5.remove(five);
        addStringToInt(five,5);

        //last element is 2
        String two = length5.get(0);
        addStringToInt(two, 2);

    }



    public void generateMappings(List<Signal> signals) {
        signals.stream().forEach(signal -> {
            int length = signal.toString().length();
            if (uniqueLengthMapping.containsKey(length)) {
                addStringToInt(signal.toString(), uniqueLengthMapping.get(length));
            }
        });

        mapNineAndZero(signals);
        mapTwoThreeAndFive(signals);

    }

    public Integer mapStringToDigit(List<Signal> signals){
        int outcome = 0;
        for (Signal s : signals) {
            String toString = s.toString();
            Integer digit = stringToInt.get(toString);
            outcome = outcome * 10 + digit;
        }
        return outcome;
    }

    public Integer mapStringToDigit(Signal s){
        return stringToInt.get(s.toString());
    }


}
