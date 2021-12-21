package com.pieterjd.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Path {
    private List<Position> positions  = new ArrayList<>();
    public Path(Path p){
        this();
        p.stream().forEach(pos->add(pos));
    }

    public Path() {
        positions = new ArrayList<>();
    }

    public void add(Position element) {
        positions.add(element);
    }

    public void remove(Position p){
        positions.remove(p);
    }

    public Stream<Position> stream() {
        return positions.stream();
    }

    public boolean isEmpty() {
        return positions.isEmpty();
    }

    public int getTotalRisk(){
        return positions.stream().mapToInt(p->p.getRisk())
                .sum();
    }

    public boolean isBetterThan(Path p){
        return getTotalRisk() <p.getTotalRisk();
    }

    public boolean containsPosition(Position p){
        return positions.contains(p);
    }

    public String toString(){
        return positions.stream()
                .map(p->p.toString())
                .collect(Collectors.joining(" -> "));
    }
}
