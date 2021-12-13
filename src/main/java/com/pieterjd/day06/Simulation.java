package com.pieterjd.day06;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Simulation {
    private List<Fish> fishes = new ArrayList<>();
    //key is number (0..8), value is the occurences of that number
    Map<Integer, BigInteger> occurences = new HashMap<>();

    public boolean add(Fish fish) {
        return fishes.add(fish);
    }

    public void initCompleted() {
        IntStream.rangeClosed(0, 8)
                .forEach(i -> occurences.put(i, BigInteger.ZERO));
        fishes.stream()
                .forEach(
                        f -> occurences.merge(f.getTimer(), BigInteger.ONE, (bg1, bg2) -> bg1.add(bg2))
                );
    }

    //more clever trick for part 2
    public BigInteger tick(int days) {
        for (int i = 1; i <= days; i++) {
            BigInteger nrZeros = occurences.get(0);
            for (int key = 0; key <= 7; key++) {
                occurences.put(key, occurences.get(key + 1));
            }
            occurences.put(8, nrZeros);
            occurences.merge(6, nrZeros, (bg1, bg2) -> bg1.add(bg2));
        }
        return occurences.keySet().stream()
                .map(key -> occurences.get(key))
                .reduce(BigInteger.ZERO, (partial_solution, next) -> partial_solution.add(next));
    }


    // brute force for part 1
    public void tick() {
        int fishToAdd = 0;

        for (Iterator<Fish> it = fishes.iterator(); it.hasNext(); ) {
            Fish f = it.next();
            if (f.canCreateNewFish()) {
                f.resetTimer();
                fishToAdd++;
            } else {
                f.decrease();
            }
        }
        IntStream.rangeClosed(1, fishToAdd)
                .forEach(i -> fishes.add(new Fish()));
    }

    public int nrOfFish() {
        return fishes.size();
    }

    public String toString() {
        String s = fishes.stream()
                .map(f -> String.valueOf(f.getTimer()))
                .collect(Collectors.joining(","));
        s = s.substring(0, Math.min(52, s.length()));
        s += String.format(" total fish: %d", nrOfFish());
        return s;
    }
}
