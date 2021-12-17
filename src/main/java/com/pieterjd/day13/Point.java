package com.pieterjd.day13;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
public class Point {
    @EqualsAndHashCode.Include
    private int row, col;
    @EqualsAndHashCode.Exclude
    @Setter
    private boolean dot;

    public Point(int row, int col) {
        this(row,col, false);
    }

    public Point(int row, int col, boolean dot) {
        this.row = row;
        this.col = col;
        this.dot = dot;
    }
    public Point addPoint(Point p){
        return new Point(getRow()+p.getRow(), getCol()+p.getCol(), true);
    }
}
