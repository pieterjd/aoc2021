package com.pieterjd.day01;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;


@Getter
public class Slice {
    private List<Integer> values;

    public Slice() {
        values = new ArrayList<>();
    }

    public Integer getLastEntry(){
        return getValues().size() == 0
            ? null
            : getValues().get(getValues().size()-1);
    }
    public void add(Integer i){
        getValues().add(i);
    }

    public int sumOfEntries(){
        return values.stream()
            .mapToInt(i -> i.intValue())
            .sum();
    }
}
