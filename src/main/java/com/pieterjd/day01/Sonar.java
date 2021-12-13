package com.pieterjd.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Sonar {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
            Sonar.class.getResourceAsStream("/day01-sonar.txt")
        ));
        List<Slice> ranges = new ArrayList<>();
        Slice currentRange = new Slice();
        for(String line = br.readLine(); line!=null; line = br.readLine()){
            Integer value = Integer.parseInt(line);
            if(currentRange.getLastEntry() == null ||
                (currentRange.getLastEntry() != null &&value > currentRange.getLastEntry())
            ){
                currentRange.add(value);
            }
            else{
                ranges.add(currentRange);
                currentRange = new Slice();
                currentRange.add(value);
            }
        }
        ranges.add(currentRange);

        int count = ranges.stream()
            .mapToInt(r -> r.getValues().size() - 1)
            .sum();
        System.out.println(String.format("# measurements larger than previous one: %d", count));
    }
}
