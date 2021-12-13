package com.pieterjd.day06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Day06 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                ClassLoader.class.getResourceAsStream("/day06-1.txt")
        ));
        Simulation sim =new Simulation();
        Arrays.stream(br.readLine().split(","))
                .map(s->Integer.parseInt(s))
                .map(i -> new Fish(i))
                .forEach(f->sim.add(f));
        sim.initCompleted();

        System.out.println(sim.tick(256));

    }

}
