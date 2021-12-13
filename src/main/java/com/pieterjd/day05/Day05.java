package com.pieterjd.day05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Day05 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                ClassLoader.class.getResourceAsStream("/day05-1.txt")
        ));
        Diagram d = new Diagram();

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            String[] coords = line.split(" -> ");
            String[] start = coords[0].split(",");
            String[] end = coords[1].split(",");
            Line l = new Line(
                    Integer.parseInt(start[0]),
                    Integer.parseInt(start[1]),
                    Integer.parseInt(end[0]),
                    Integer.parseInt(end[1])
            );
            d.addPositions(l);
        }
        //System.out.println(d);
        System.out.println(String.format("Points with at least 2 lines: %d",d.getPositionsWithAtLeast2Lines().size()));
    }

}
