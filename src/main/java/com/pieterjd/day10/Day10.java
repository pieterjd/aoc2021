package com.pieterjd.day10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                ClassLoader.class.getResourceAsStream("/day10.txt")
        ));

        LineParser lp = new LineParser();
        Map<Character, Integer> points = new HashMap<>();
        points.put(')', 3);
        points.put(']', 57);
        points.put('}', 1197);
        points.put('>', 25137);

        List<ParseInformation> pis = new ArrayList<>();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            ParseInformation pi = lp.parseLine(line);
            pis.add(pi);

        }
        int sum = pis.stream()
                .filter(p -> p.isCorrupted())
                .mapToInt(p -> points.get(p.getFound()))
                .sum();

        System.out.println("Points: " + sum);

        // part 2
        Map<Character, Integer> points2 = new HashMap<>();
        points2.put(')', 1);
        points2.put(']', 2);
        points2.put('}', 3);
        points2.put('>', 4);
        List<Long> sortedScores = pis.stream()
                .filter(p -> !p.isStackEmpty() && p.isSuccess())
                .mapToLong(p -> p.completionScore())
                .sorted()
                .mapToObj(i -> Long.valueOf(i))
                .collect(Collectors.toList());
        System.out.println("Completion points: "+sortedScores);
        int midIndex = (sortedScores.size()/2);
        System.out.println("Midpoint: "+ sortedScores.get(midIndex));
    }
}
