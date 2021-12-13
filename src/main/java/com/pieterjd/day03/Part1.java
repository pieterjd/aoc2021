package com.pieterjd.day03;

import com.pieterjd.day02.PositionWithAim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
            ClassLoader.class.getResourceAsStream("/day03-1.txt")
        ));
        List<BinaryNumber> numbers = new ArrayList<>();
        for(String line=br.readLine();line != null; line=br.readLine()){
            numbers.add(new BinaryNumber(line));
        }

        BinaryNumberStatistics bns = new BinaryNumberStatistics(numbers);
        BinaryNumber gamma = bns.getGamma();
        BinaryNumber epsilon = gamma.inverse();
        System.out.println(String.format("gamma: %s - %d",gamma,gamma.toDecimal()));
        System.out.println(String.format("epsil: %s - %d",epsilon,epsilon.toDecimal()));
        System.out.println("Answer: "+gamma.toDecimal()* epsilon.toDecimal());

        System.out.println("Part 2");


        BinaryNumber oxygen = bns.getOxygen();
        BinaryNumber co2 = bns.getCO2();
        System.out.println(String.format("oxygen: %s - %d",oxygen,oxygen.toDecimal()));
        System.out.println(String.format("co2: %s - %d",co2,co2.toDecimal()));
        System.out.println("Answer part 2: "+oxygen.toDecimal()* co2.toDecimal());
    }
}
