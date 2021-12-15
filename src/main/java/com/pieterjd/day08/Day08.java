package com.pieterjd.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Day08 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                ClassLoader.class.getResourceAsStream("/day08-1.txt")
        ));
        List<InputEntry> entries = new ArrayList<>();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            InputEntry.InputEntryBuilder builder = InputEntry.builder();
            String[] parts = line.split((" \\| "));
            Arrays.stream(parts[0].split(" "))
                    .forEach(s -> builder.input(new Signal(s)));
            Arrays.stream(parts[1].split(" "))
                    .forEach(s -> builder.output(new Signal(s)));
            entries.add(builder.build());
        }
        Counter counter = new Counter();

        entries.stream()
                .map(e -> e.getOutputs())
                .flatMap(Collection::stream)
                .map(signal -> new Digit(signal))
                .forEach(d -> counter.addToCounts(d));
        System.out.println(counter.getSummedCount());

        // part 2

        int sum = entries.stream()
                .mapToInt(e -> {
                    Mapper m =new Mapper();
                    m.generateMappings(e.getInputs());
                    return m.mapStringToDigit(e.getOutputs());
                })
                .sum();
        System.out.println(String.format("Sum of numbers is: %d", sum));
    }
}
