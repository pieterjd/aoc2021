package com.pieterjd.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class Day11 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                ClassLoader.class.getResourceAsStream("/day11.txt")
        ));

        Grid g = new Grid();
        int row = 0;
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            int col = 0;
            for (int i = 0; i < line.length(); i++) {
                int energy = Integer.valueOf("" + line.charAt(i));
                Octopus o = new Octopus(row, col, energy);
                g.add(o);
                col++;
            }
            row++;
        }
        /** comment out for part 2
        IntStream.rangeClosed(1, 100)
                .forEach(i -> {
                    g.step();
                    //System.out.println(g);
                    if(i%10 == 0) System.out.println(String.format("Step %d: %d",i,g.getNrOfFlashes()));
                });
         **/


        //part 2
        System.out.println(g.firstAllFlashStep());

    }
}
