package com.pieterjd.day15;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter

public class Position {

    private int row,col;

    private int risk;
    @Setter
    private int distanceToStart = Integer.MAX_VALUE;

    public Position(int row, int col, int risk) {
        this.row = row;
        this.col = col;
        this.risk = risk;
    }

    public String toString(){
        return String.format("(%d,%d, %d)",getRow(),getCol(),getRisk());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
