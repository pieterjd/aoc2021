package com.pieterjd.day02;

import com.pieterjd.day01.Sonar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculatePosition {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
            Sonar.class.getResourceAsStream("/day02-1-test.txt")
        ));
        Position current = new Position();
        for(String line=br.readLine();line != null; line=br.readLine()){
            String[] parts = line.split(" ");
            current = current.getNext(parts[0], Integer.parseInt(parts[1]));
        }

        System.out.printf(String.format("Current position: %s - multiply = %d",
            current.toString(), current.getDepth() * current.getHorizontal()));
    }
}
