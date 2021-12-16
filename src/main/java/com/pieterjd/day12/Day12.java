package com.pieterjd.day12;

import com.pieterjd.day09.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                ClassLoader.class.getResourceAsStream("/day12-test.txt")
        ));

        CaveMap m =new CaveMap();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            String[] parts = line.split("-");
            Cave c1 = new Cave(parts[0],parts[0].equals(parts[0].toLowerCase()));
            Cave c2 = new Cave(parts[1],parts[1].equals(parts[1].toLowerCase()));
            m.addNode(c1);
            m.addNode(c2);
            m.putEdgeValue(c1,c2,0);
        }
        System.out.println(m.getGraph().adjacentNodes(new Cave("start")).stream().map(c->c.toString()).collect(Collectors.joining(", ")));
        PathFinder pf = new PathFinder(m);
        List<Path> allPaths = pf.findAllPaths();
        System.out.println(allPaths.size());
    }
}
