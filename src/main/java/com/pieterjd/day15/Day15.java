package com.pieterjd.day15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day15 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                ClassLoader.class.getResourceAsStream("/day15.txt")
        ));
        int row = 0;
        List<Position> positions = new ArrayList<>();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            for (int i = 0; i < line.length(); i++) {
                Position p = new Position(row, i, Integer.valueOf("" + line.charAt(i)));
                positions.add(p);

            }
            row++;
        }
        Cavern c = new Cavern(positions);


        c.repeatToRight(4);
        c.repeatToBottom(4);
        //c.buildGraph();
        PathFinder pf = new PathFinder(c);
        pf.doDijkstra();
        System.out.println(pf.getDistances().get(new Position(c.getOriginalRowNr(),c.getOriginalColNr(),9)));


    }
}
