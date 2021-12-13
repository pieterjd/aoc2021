package com.pieterjd.day04;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter

public class BingoPosition {
    private int row, col;
    private int value;
    @Builder.Default
    @Setter
    private boolean marked = false;
}
