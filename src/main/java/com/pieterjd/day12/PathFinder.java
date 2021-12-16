package com.pieterjd.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PathFinder {
    private CaveMap map;
    public static final Cave START = new Cave("start");
    public static final Cave END = new Cave("end");

    public PathFinder(CaveMap map) {
        this.map = map;
    }

    public List<Path> findAllPaths(){
        return findAllPaths(START);
    }

    public List<Path> findAllPaths(Cave start) {

        List<Path> allPaths = findAllPaths(start, new ArrayList<>());
        allPaths.forEach(p->p.addInFront(start));
        return allPaths;
    }

    public List<Path> findAllPaths(Cave start, List<Cave> visited) {
        List<Path> result = new ArrayList<>();

        if (start.equals(END)) {
            Path p = new Path();
            result.add(p);
        } else {

            List<Cave> neighbours = map.getGraph().adjacentNodes(start).stream()
                    .filter(c -> !c.equals(START))
                    .filter(c -> !c.isSmall() || (c.isSmall() && !visited.contains(c)))
                    .collect(Collectors.toList());
            neighbours.stream()
                    .forEach(c -> {
                        visited.add(c);
                        List<Path> allPaths = findAllPaths(c, visited);
                        allPaths.stream()
                                .forEach(path -> {
                                    path.addInFront(c);
                                    result.add(path);
                                });
                        visited.remove(c);
                    });
        }

        return result;
    }
}
