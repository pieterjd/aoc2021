package com.pieterjd.day15;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.pieterjd.day09.Point;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cavern {
    @Getter
    private List<Position> positions;
    @Getter
    MutableValueGraph<Position, Integer> graph;
    @Getter
    private int originalRowNr, originalColNr;


    public Cavern(List<Position> positions) {
        this.positions = positions;
        originalRowNr = positions.stream().mapToInt(p -> p.getRow()).max().orElse(0);
        originalColNr = positions.stream().mapToInt(p -> p.getCol()).max().orElse(0);
        buildGraph();

    }

    public void buildGraph() {
        originalRowNr = positions.stream().mapToInt(p -> p.getRow()).max().orElse(0);
        originalColNr = positions.stream().mapToInt(p -> p.getCol()).max().orElse(0);
        graph = ValueGraphBuilder.directed().build();
        positions.stream().forEach(pos -> {
            List<Position> neighbours = get4Neighbours(pos);
            neighbours.forEach(n -> graph.putEdgeValue(pos, n, n.getRisk()));
        });
    }

    public void updateRowColNr(){
        originalRowNr = positions.stream().mapToInt(p -> p.getRow()).max().orElse(0);
        originalColNr = positions.stream().mapToInt(p -> p.getCol()).max().orElse(0);
    }

    public void repeatToRight(int repeats) {
        int maxCol = positions.stream().mapToInt(p -> p.getCol()).max().orElse(0);
        Set<Position> newPoints = positions.stream()
                .filter(p -> p.getCol() <= originalColNr)
                .map(p -> {
                    List<Position> pos = new ArrayList<>();
                    for (int i = 1; i <= repeats; i++) {
                        int newRisk = p.getRisk() + i;
                        if (newRisk >= 10) newRisk = newRisk - 9;
                        int newRow = p.getRow();
                        int newCol = p.getCol() + i +originalColNr * i;
                        pos.add(new Position(newRow, newCol, newRisk));
                    }
                    return pos;

                }).flatMap(Collection::stream).collect(Collectors.toSet());
        newPoints.forEach(p -> positions.add(p));
        //maxColNr = 2*maxRowNr +1;
    }


    public void repeatToBottom(int repeats) {
        int maxCol = positions.stream().mapToInt(p -> p.getRow()).max().orElse(0);
        Set<Position> newPoints = positions.stream()
                .filter(p -> p.getRow() <= originalRowNr)
                .map(p -> {
                    List<Position> pos = new ArrayList<>();
                    for (int i = 1; i <= repeats; i++) {
                        int newRisk = p.getRisk() + i;
                        if (newRisk >= 10) newRisk = newRisk - 9;
                        int newRow = p.getRow()+i + originalRowNr * i;
                        int newCol = p.getCol();
                        pos.add(new Position(newRow, newCol, newRisk));
                    }
                    return pos;

                }).flatMap(Collection::stream).collect(Collectors.toSet());
        newPoints.forEach(p -> positions.add(p));
    }

    public int size() {
        return positions.size();
    }

    public List<Position> get4Neighbours(Position p) {
        int colMin = Math.max(0, p.getCol() - 1);
        int colMax = Math.min(originalColNr, p.getCol() + 1);

        int rowMin = Math.max(0, p.getRow() - 1);
        int rowMax = Math.min(originalRowNr, p.getRow() + 1);
        List<Position> result = new ArrayList<>();

        result.add(getPosition(rowMin, p.getCol()));
        result.add(getPosition(rowMax, p.getCol()));
        result.add(getPosition(p.getRow(), colMin));
        result.add(getPosition(p.getRow(), colMax));
        return result.stream().filter(n->!n.equals(p)).distinct().collect(Collectors.toList());
    }

    public String getRowString(int row) {
        return positions.stream()
                .filter((p -> p.getRow() == row))
                .sorted(Comparator.comparing(p -> p.getCol()))
                .map(p -> "" + p.getRisk())
                .collect(Collectors.joining(""));
    }

    public String toString() {
        int rows = positions.stream().mapToInt(p->p.getRow()).max().orElse(0);
        int cols = positions.stream().mapToInt(p->p.getCol()).max().orElse(0);
        return IntStream.rangeClosed(0, rows)
                .mapToObj(i -> getRowString(i ))
                .collect(Collectors.joining("\n"));
    }

    public Position getPosition(int row, int col) {
        return positions.stream().filter(p -> p.getRow() == row && p.getCol() == col).findFirst().orElse(null);
    }

}
