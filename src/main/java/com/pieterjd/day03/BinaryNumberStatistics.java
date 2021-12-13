package com.pieterjd.day03;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class BinaryNumberStatistics {
    private List<BinaryNumber> numbers;

    // returns the most common value at position pos
    public int checkPosition(int pos){
        Map<Integer, List<BinaryNumber>> collect = numbers.stream()
            .collect(Collectors.groupingBy(bn -> bn.getDigits().get(pos)))
            ;
        int nrZeros = collect.get(0).size();
        int nrOnes = collect.get(1).size();
        return nrOnes<nrZeros ? 0 : 1;
    }

    // if >0, more 1s; if =0, equal; if <0, more 0s
    public int comparePosition(List<BinaryNumber> numbers, int pos){
        Map<Integer, List<BinaryNumber>> collect = numbers.stream()
            .collect(Collectors.groupingBy(bn -> bn.getDigits().get(pos)))
            ;
        int nrZeros = collect.get(0).size();
        int nrOnes = collect.get(1).size();
        return nrOnes-nrZeros;
    }


    public BinaryNumber getGamma(){
        int bnLength = numbers.get(0).size();
        BinaryNumber bn = new BinaryNumber();
        for(int i=0;i<bnLength;i++){
            bn.add(checkPosition(i));
        }
        return bn;
    }

    BinaryNumber getOxygen(){
        return getRating(compareValue ->compareValue>=0 ? 1 : 0);
    }
    BinaryNumber getCO2(){
        return getRating(compareValue ->compareValue>=0 ? 0 : 1);
    }


    BinaryNumber getRating(IntFunction<Integer> lambda){
        List<BinaryNumber> filtered = numbers.stream().collect(Collectors.toList());
        int bnLength = numbers.get(0).size();
        for(int i=0;i<bnLength && filtered.size()!=1;i++){
            int compareValue = comparePosition(filtered, i);
            int filterValue = lambda.apply(compareValue);
            int finalI = i;
            filtered = filtered.stream()
                .filter(bn->bn.get(finalI)== filterValue)
                .collect(Collectors.toList());
            //System.out.println(filtered);
        }
        return filtered.get(0);

    }

}
