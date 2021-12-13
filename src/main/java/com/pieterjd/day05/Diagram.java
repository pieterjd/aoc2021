package com.pieterjd.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Diagram {
    private List<Position> positions = new ArrayList<>();

    public void addPositions(Line line) {
        List<Position> toAdd = line.generatePositions();
        for (Position p : toAdd) {
            int index = positions.indexOf(p);
            if (index > -1) {
                positions.get(index).add(line);
            } else {
                p.add(line);
                positions.add(p);
            }
        }
    }

    private int minX() {
        return positions.stream()
                .mapToInt(p -> p.getX())
                .min()
                .getAsInt();
    }

    private int maxX() {
        return positions.stream()
                .mapToInt(p -> p.getX())
                .max()
                .getAsInt();
    }

    private int minY() {
        return positions.stream()
                .mapToInt(p -> p.getY())
                .min()
                .getAsInt();
    }

    private int maxY() {
        return positions.stream()
                .mapToInt(p -> p.getY())
                .max()
                .getAsInt();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = minY(); i <= maxY(); i++) {
            for (int j = minX(); j <= maxX(); j++) {
                Position current = new Position(j,i);
                Position position = positions.stream()
                        .filter(p -> p.equals(current))
                        .findFirst()
                        .orElse(null);
                sb.append(
                        position == null
                                ? "."
                                : position.getLines().size()
                );
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public List<Position> getPositionsWithAtLeast2Lines(){
        return positions.stream()
                .filter(p->p.getLines().size()>=2)
                .collect(Collectors.toList());
    }

}
