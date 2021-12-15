package com.pieterjd.day09;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Map {
    private List<Point> points = new ArrayList<>();
    private int nrCols = 0;

    private int getMaxRow() {
        return points.stream().mapToInt(p -> p.getRow()).max().orElse(-1);
    }

    public void addRow(String s) {
        if (nrCols == 0) nrCols = s.length();
        int row = getMaxRow() + 1;
        for (int i = 0; i < s.length(); i++) {
            int height = Integer.parseInt("" + s.charAt(i));
            int col = i;
            points.add(new Point(row, col, height, false));
        }
    }

    private Point getPoint(int row, int col) {
        Point p = new Point(row, col, 0, false);
        Optional<Point> first = points.stream()
                .filter(point -> p.onSamePosition(point))
                .findFirst();
        if (!first.isPresent()) {
            System.out.println("Point not found! " + p);
        }
        return first.orElse(null);

    }

    private List<Point> get4Neighbours(Point p) {
        int colMin = Math.max(0, p.getCol() - 1);
        int colMax = Math.min(nrCols - 1, p.getCol() + 1);

        int rowMin = Math.max(0, p.getRow() - 1);
        int rowMax = Math.min(getMaxRow(), p.getRow() + 1);
        List<Point> result = new ArrayList<>();

        result.add(getPoint(rowMin, p.getCol()));
        result.add(getPoint(rowMax, p.getCol()));
        result.add(getPoint(p.getRow(), colMin));
        result.add(getPoint(p.getRow(), colMax));
        return result.stream().distinct().collect(Collectors.toList());

    }

    private List<Point> get8Neighbours(Point p){
        int colMin = Math.max(0, p.getCol() - 1);
        int colMax = Math.min(nrCols - 1, p.getCol() + 1);

        int rowMin = Math.max(0, p.getRow() - 1);
        int rowMax = Math.min(getMaxRow(), p.getRow() + 1);

        return points.stream()
                .filter(point-> !p.onSamePosition(point))
                .filter(point -> colMin<=point.getCol() && point.getCol()<=colMax)
                .filter(point -> rowMin<=point.getRow() && point.getRow()<=rowMax)
                .collect(Collectors.toList());
    }

    public void calcLowPoints() {
        points.stream()
                .forEach(p -> {
                    List<Point> neighbours = get8Neighbours(p);
                    boolean lowPoint = neighbours.stream()
                            .allMatch(neighbour -> neighbour.getHeight() > p.getHeight());
                    p.setLowPoint(lowPoint);
                });
    }

    public Set<Point> getLowPoints() {
        return points.stream().filter(p -> p.isLowPoint()).collect(Collectors.toSet());
    }

    private String getRowString(int row) {
        return points.stream()
                .filter((p -> p.getRow() == row))
                .sorted(Comparator.comparing(p -> p.getCol()))
                .map(p -> "" + p.getHeight())
                .collect(Collectors.joining(""));
    }

    public String toString() {
        return IntStream.rangeClosed(0, getMaxRow())
                .mapToObj(i -> getRowString(i))
                .collect(Collectors.joining("\n"));
    }

    public Basin getBasin(Point lowPoint) {
        if (!lowPoint.isLowPoint()) return null;
        Basin b = new Basin();
        b.add(lowPoint);

        Predicate<Point> notMaxHeight = (p -> p.getHeight() < 9);
        Predicate<Point> higherThanLowPoint = (p -> p.getHeight() > lowPoint.getHeight());
        Predicate<Point> neighbourDefinition = notMaxHeight.and(higherThanLowPoint);

        List<Point> neighbours = get4Neighbours(lowPoint).stream()
                .filter(neighbourDefinition)
                .collect(Collectors.toList());
        while (!neighbours.isEmpty()) {
            b.addAll(neighbours);
            neighbours = neighbours.stream()
                    .map(n -> {
                        //neighbours of neighbours
                        List<Point> nofn = get4Neighbours(n);
                        return nofn.stream()
                                .filter(nn -> !b.contains(nn))
                                .filter(nn -> nn.getHeight() > n.getHeight())
                                .filter(nn -> nn.getHeight() < 9)
                                .collect(Collectors.toList());

                    })
                    .flatMap(Collection::stream)
                    .distinct()
                    .collect(Collectors.toList());
        }
        return b;
    }
}
