package com.pieterjd.day12;

import java.util.*;
import java.util.stream.Collectors;

public class PathFinder {
    private CaveMap map;
    public static final Cave START = new Cave("start");
    public static final Cave END = new Cave("end");

    public PathFinder(CaveMap map) {
        this.map = map;
    }

    public List<Path> findAllPaths(){
        return findAllPaths(START,1);
    }
    public List<Path> findAllPaths(int maxSmallVisits){
        return findAllPaths(START,maxSmallVisits);
    }
    public List<Path> findAllPaths(Cave start,int maxSmallVisit) {
        Map<Cave, Integer> visits = map.nodes().stream()
                .collect(Collectors.toMap(
                        c -> c,
                        c -> 0
                ));
        List<Path> allPaths = findAllPaths(start, new ArrayList<>(), visits,maxSmallVisit);
        allPaths.forEach(p->p.addInFront(start));
        return allPaths;
    }

    public List<Path> findAllPaths(Cave start, List<Cave> visited, Map<Cave, Integer> counts,int maxSmallVisit) {
        List<Path> result = new ArrayList<>();

        if (start.equals(END)) {
            Path p = new Path();
            result.add(p);
        } else {
            boolean hasSmallCaveWith2Visits = counts.keySet().stream()
                    .anyMatch(c->c.isSmall() && counts.get(c) == maxSmallVisit);
            List<Cave> neighbours = map.getGraph().adjacentNodes(start).stream()
                    .filter(c -> !c.equals(START))
                    .filter(c -> !c.isSmall() || (
                            (c.isSmall() && !hasSmallCaveWith2Visits && counts.get(c)<maxSmallVisit) ||
                                    (c.isSmall() && hasSmallCaveWith2Visits && counts.get(c)<1)
                    ))
                    .collect(Collectors.toList());
            neighbours.stream()
                    .forEach(c -> {
                        visited.add(c);
                        counts.merge(c, 1, (a,b)->a+b);
                        List<Path> allPaths = findAllPaths(c, visited, counts,maxSmallVisit);
                        allPaths.stream()
                                .forEach(path -> {
                                    path.addInFront(c);
                                    result.add(path);
                                });
                        visited.remove(c);
                        int countOfc = counts.get(c);
                        counts.put(c, countOfc-1);
                    });
        }

        return result;
    }
}
