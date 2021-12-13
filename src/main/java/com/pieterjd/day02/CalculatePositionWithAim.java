package com.pieterjd.day02;

import com.pieterjd.day01.Sonar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculatePositionWithAim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
            ClassLoader.class.getResourceAsStream("/day02-1.txt")
        ));
        PositionWithAim current = new PositionWithAim();
        for(String line=br.readLine();line != null; line=br.readLine()){
            String[] parts = line.split(" ");
            PositionWithAim next = current.getNext(parts[0], Integer.parseInt(parts[1]));
            System.out.println(String.format("%s : %s", parts[0], next.toString()));
            current = next;
        }

        System.out.printf(String.format("Current position: %s - multiply = %d",
            current.toString(), current.getDepth() * current.getHorizontal()));
    }
}
