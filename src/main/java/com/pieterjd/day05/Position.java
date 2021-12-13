package com.pieterjd.day05;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Position {
    private int x, y;
    @ToString.Exclude
    private List<Line> lines = new ArrayList<>();

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int nrOfLines() {
        return lines.size();
    }

    public boolean add(Line line) {
        return lines.add(line);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return getX() == position.getX() && getY() == position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
