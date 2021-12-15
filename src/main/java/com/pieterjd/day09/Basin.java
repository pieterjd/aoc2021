package com.pieterjd.day09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Basin {
    private List<Point> points = new ArrayList<>();

    public int size() {
        return points.size();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public boolean contains(Point o) {
        return points.stream()
                .anyMatch(p->p.onSamePosition(o));
    }

    public boolean add(Point point) {
        return points.add(point);
    }

    public boolean addAll(Collection<? extends Point> c) {
        return points.addAll(c);
    }

    public Stream<Point> stream() {
        return points.stream();
    }
    public String toString(){
        return points.stream()
                .map(p->p.toString())
                .collect(Collectors.joining("\n"));
    }
}
