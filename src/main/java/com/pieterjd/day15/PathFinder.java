package com.pieterjd.day15;

import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class PathFinder {
    @Getter
    private Cavern c;


    Map<Position,Position> prev= new HashMap<>();
    @Getter
    Map<Position,Integer> distances= new HashMap<>();


    private Path currentPath;

    private Path bestPath;

    public Path getBestPath() {
        return bestPath;
    }

    public PathFinder(Cavern c) {
        this.c = c;
        currentPath = new Path();
        bestPath = new Path();
    }

    public void findBestPath(){
        Position start = c.getPosition(0,0);
        Position end = c.getPosition(c.getOriginalRowNr(), c.getOriginalColNr());
        findBestPath(start,end, new Path());
        bestPath.add(end);
        bestPath.remove(start);
    }

    public void doDijkstra(){
        int rows = c.getPositions().stream().mapToInt(p->p.getRow()).max().orElse(0);
        int cols = c.getPositions().stream().mapToInt(p->p.getCol()).max().orElse(0);
        c.updateRowColNr();
        doDijkstra(c.getPosition(0,0),c.getPosition(rows, cols));
    }

    public void doDijkstra(Position start,Position end){

        PriorityQueue<Position> q = new PriorityQueue<Position>(11,Comparator.comparing(p->p.getDistanceToStart()));
        start.setDistanceToStart(0);
        c.getPositions().forEach(n->q.add(n));

        while(!q.isEmpty()){
            Position first = q.poll();
            //System.out.println(String.format("Checking successors of %s: %s",first,c.get4Neighbours(first)));
            c.get4Neighbours(first).forEach(v->{
                int cumulRisk = first.getDistanceToStart() + v.getRisk();
                if(cumulRisk< v.getDistanceToStart()){
                    v.setDistanceToStart(cumulRisk);
                    prev.put(v,first);
                    q.remove(v);
                    q.add(v);

                    distances.put(v,cumulRisk);
                }
                if(v.equals(end)) System.out.println("CumulRisk till end:" +cumulRisk);
            });
        }
        System.out.println(prev);
    }


    public int getTotalRisk(){
        return c.getPosition(c.getOriginalRowNr(), c.getOriginalColNr()).getDistanceToStart();
    }



    public void findBestPath(Position start, Position end, Path currentPath) {
        if (start.equals(end)) {
            System.out.println(String.format("Found path! total risk is %d",currentPath.getTotalRisk()));
            if(bestPath.isEmpty()){
                bestPath = new Path(currentPath);
            }
            else{
                if(currentPath.isBetterThan(bestPath)) bestPath = new Path(currentPath);
            }
        } else {
            //find neigbours not yet in path
            List<Position> neighbours = c.getGraph().successors(start).stream()
                    .filter(p -> !currentPath.containsPosition(p))
                    .collect(Collectors.toList());
            if (!bestPath.isEmpty()) neighbours = neighbours.stream()
                    .filter(p -> currentPath.getTotalRisk() + p.getRisk() < bestPath.getTotalRisk())
                    .collect(Collectors.toList());
            //System.out.println(String.format("adding %s to path", start));
            currentPath.add(start);
            neighbours.forEach(n -> findBestPath(n, end, currentPath));
            //System.out.println(String.format("removing %s to path", start));
            currentPath.remove(start);
        }
    }



}
