package com.pieterjd.day13;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FoldInstruction {
    private char axis;
    private int value;
    public boolean isRowFold(){
        return axis == 'y';
    }
    public String toString(){
        return String.format("folding over %s - isRow?: %s - nr %d",axis, isRowFold(), value);
    }
}
