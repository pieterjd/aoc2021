package com.pieterjd.day09;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
public class Point {
    @EqualsAndHashCode.Include
    private int row, col;
    private int height;
    @Setter
    @ToString.Exclude
    private boolean isLowPoint = false;

    public int getRisk(){
        return getHeight() + 1;
    }

    public boolean onSamePosition(Point p){
        return p.getRow() == getRow() && p.getCol() ==getCol();
    }
}
