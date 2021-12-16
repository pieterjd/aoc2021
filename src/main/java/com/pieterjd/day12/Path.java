package com.pieterjd.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Path {
    private List<Cave> caves = new ArrayList<>();

    public Path() {
    }

    public Path(Path p){
        p.stream().forEach(c->add(c));
    }

    public int size() {
        return caves.size();
    }

    public long countOccurence(Cave cave){
        return caves.stream()
                .filter(c->c.equals(cave))
                .count();
    }
    public boolean isEmpty() {
        return caves.isEmpty();
    }



    public boolean contains(Object o) {
        return caves.contains(o);
    }

    public void addInFront(Cave c){
        add(0, c);
    }
    public void add(int index, Cave element) {
        caves.add(index, element);
    }

    public boolean add(Cave cave) {
        return caves.add(cave);
    }

    public boolean removeIf(Predicate<? super Cave> filter) {
        return caves.removeIf(filter);
    }

    public Stream<Cave> stream() {
        return caves.stream();
    }

    public void forEach(Consumer<? super Cave> action) {
        caves.forEach(action);
    }

    public String toString(){
        return caves.stream()
                .map(c->c.getName())
                .collect(Collectors.joining(","));
    }
}
