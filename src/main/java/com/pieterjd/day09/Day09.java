package com.pieterjd.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day09 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                ClassLoader.class.getResourceAsStream("/day09.txt")
        ));
        Map m = new Map();
        for(String line=br.readLine();line!=null;line=br.readLine()){
            m.addRow(line);
        }
        //System.out.println(m);
        m.calcLowPoints();
        System.out.println(m.getLowPoints());
        int sum = m.getLowPoints().stream()
                .mapToInt(p -> p.getRisk())
                .sum();

        System.out.println(String.format("Sum of risk of low points: %d",sum));
        //part 2
        List<Basin> basins = m.getLowPoints().stream()
                .map(lp -> m.getBasin(lp))
                .sorted(Comparator.comparing(Basin::size).reversed())
                .collect(Collectors.toList());
        int product = basins.stream()
                .limit(3)
                .mapToInt(b -> b.size())
                .reduce(1, (a, b) -> a * b);
        System.out.println(String.format("Basins: %s",basins.stream().map(b->String.valueOf(b.size())).collect(Collectors.joining(" "))));
        System.out.println(String.format("Product of size of 3 largest basins: %d",product));

    }
}
