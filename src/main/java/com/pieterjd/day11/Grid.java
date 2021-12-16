package com.pieterjd.day11;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Grid {
    private static final int COLS = 10;
    private List<Octopus> octos = new ArrayList<>();
    @Getter
    private long nrOfFlashes = 0;

    public void step() {
        octos.forEach(o -> {
            o.initStep();
            o.increaseEnergy();
        });

        List<Octopus> canFlash = canFlash();
        while (!canFlash.isEmpty()) {
            canFlash.forEach(o -> o.flash());

            canFlash.stream()
                    .map(o -> get8Neighbours(o))
                    .flatMap(Collection::stream)
                    .forEach(o -> o.increaseEnergy());

            canFlash = canFlash();
        }
        nrOfFlashes += nrOfFlashes();
    }

    public int firstAllFlashStep() {
        int step = 0;
        boolean allFlash = allFlashing();
        while (!allFlash) {
            step();
            step++;
            allFlash = allFlashing();
        }
        return step;
    }

    public boolean allFlashing() {
        return octos.stream()
                .allMatch(o -> o.getEnergy() == 0);
    }

    public List<Octopus> canFlash() {
        return octos.stream()
                .filter(o -> o.canFlash())
                .collect(Collectors.toList());
    }

    private long nrOfFlashes() {
        return octos.stream()
                .filter(o -> o.isFlashedDuringStep())
                .count();
    }


    private List<Octopus> get8Neighbours(Octopus o) {
        int colMin = Math.max(0, o.getCol() - 1);
        int colMax = Math.min(COLS - 1, o.getCol() + 1);

        int rowMin = Math.max(0, o.getRow() - 1);
        int rowMax = Math.min(getMaxRow(), o.getRow() + 1);

        return octos.stream()
                .filter(octo -> !o.onSamePosition(octo))
                .filter(octo -> colMin <= octo.getCol() && octo.getCol() <= colMax)
                .filter(octo -> rowMin <= octo.getRow() && octo.getRow() <= rowMax)
                .collect(Collectors.toList());
    }


    public int size() {
        return octos.size();
    }

    public boolean isEmpty() {
        return octos.isEmpty();
    }

    public boolean contains(Object o) {
        return octos.contains(o);
    }

    public boolean add(Octopus octopus) {
        return octos.add(octopus);
    }

    public Stream<Octopus> stream() {
        return octos.stream();
    }

    public void forEach(Consumer<? super Octopus> action) {
        octos.forEach(action);
    }

    private String getRowString(int row) {
        return octos.stream()
                .filter((p -> p.getRow() == row))
                .map(p -> "" + p.getEnergy())
                .collect(Collectors.joining());
    }

    public String toString() {
        int maxRow = getMaxRow();


        return IntStream.rangeClosed(0, maxRow)
                .mapToObj(i -> getRowString(i))
                .collect(Collectors.joining("\n")) + "\n";
    }

    private int getMaxRow() {
        return octos.stream()
                .mapToInt(o -> o.getRow())
                .max()
                .orElse(0);
    }
}
