package com.pieterjd.day14;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Match {
    private Rule rule;
    private int index;
    public boolean isMatch(){
        return index>=0;
    }
}
