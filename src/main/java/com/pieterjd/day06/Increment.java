package com.pieterjd.day06;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Increment {
    private int incrementValue;
    private int validStartingFrom;
}
