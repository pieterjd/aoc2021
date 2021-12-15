package com.pieterjd.day08;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class InputEntry {
    @Singular
    private List<Signal> inputs = new ArrayList<>();
    @Singular
    private List<Signal> outputs = new ArrayList<>();
}
