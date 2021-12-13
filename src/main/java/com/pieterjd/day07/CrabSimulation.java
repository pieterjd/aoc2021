package com.pieterjd.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CrabSimulation {
    private List<Crab> crabs;
    private List<Integer> positions;

    private int bestPosition;
    private int fuelSpent;

    public CrabSimulation() {
        crabs = new ArrayList<>();
        positions = new ArrayList<>();
    }

    public void add(Crab c) {
        crabs.add(c);
        positions.add(c.getStartPosition());
    }

    public Map<Integer, Integer> computeBestAlignment() {
        Map<Integer, Integer> fuelSpent = new TreeMap<>();
        List<Integer> candidatePositions = crabs.stream()
                .map(c -> c.getStartPosition())
                .collect(Collectors.toList());

        for (Integer candidate : candidatePositions) {
            crabs.forEach(c->c.resetNrOfStepChanges());
            crabs.forEach(c->c.moveToPosition(candidate));
            int sum = crabs.stream()
                    .mapToInt(c -> c.getFuelCost())
                    .sum();
            fuelSpent.put( sum, candidate);
        }
        return fuelSpent;

    }
}
