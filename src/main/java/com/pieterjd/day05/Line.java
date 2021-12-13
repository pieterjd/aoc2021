package com.pieterjd.day05;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
public class Line {
    private int x1, y1, x2, y2;
    private boolean isHorizontalLine, isVerticalLine;

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        isHorizontalLine = y1 == y2;
        isVerticalLine = x1 == x2;
    }

    public boolean contains(Position p) {
        return isHorizontalLine
                ? p.getY() == getY1()
                : isVerticalLine ? p.getX() == getX1() : false;
    }

    public int minX() {
        return Math.min(getX1(), getX2());
    }

    public int maxX() {
        return Math.max(getX1(), getX2());
    }

    public int minY() {
        return Math.min(getY1(), getY2());
    }

    public int maxY() {
        return Math.max(getY1(), getY2());
    }

    public int getYofMinX(){
        int minx=minX();
        return minx==getX1() ? getY1() : getY2();
    }

    public List<Position> generatePositions() {
        List<Position> result = new ArrayList<>();
        if (isHorizontalLine()) {
            IntStream.rangeClosed(minX(), maxX())
                    .forEach(x -> result.add(new Position(x, getY1())));
        }
        else if (isVerticalLine()) {
            IntStream.rangeClosed(minY(), maxY())
                    .forEach(y -> result.add(new Position(getX1(), y)));
        }
        else{
            //diagonal case
            int nrIncrements = Math.abs(getX2()-getX1());
            int deltaX=(getX2()-getX1())/nrIncrements;
            int deltaY=(getY2()-getY1())/nrIncrements;
            IntStream.rangeClosed(0, nrIncrements)
                    .forEach(i -> result.add(new Position(getX1()+i*deltaX,getY1()+i*deltaY)));

        }

        return result;
    }
}
