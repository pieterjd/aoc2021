package com.pieterjd.day07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day07 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                ClassLoader.class.getResourceAsStream("/day07-1.txt")
        ));
        List<Crab> crabs = Arrays.stream(br.readLine().split(","))
                .map(s -> new Crab(Integer.parseInt(s)))
                .collect(Collectors.toList());
        CrabSimulation cs = new CrabSimulation();
        crabs.forEach(c->cs.add(c));

        Map<Integer, Integer> fuelSpent = cs.computeBestAlignment();
        System.out.println(fuelSpent);
    }
}
