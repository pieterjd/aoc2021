package com.pieterjd.day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day13 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                ClassLoader.class.getResourceAsStream("/day13.txt")
        ));

        Paper p = new Paper();
        List<FoldInstruction> foldings = new ArrayList<>();
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if(line.contains(",")){
                String[] parts = line.split(",");
                p.addPoint(new Point(Integer.parseInt(parts[1]),Integer.parseInt(parts[0]), true));
            }
            if(line.contains("fold along")){
                boolean containsx=line.contains("x");
                char axis = containsx ? 'x' : 'y';
                int indexEqualSign=line.indexOf('=');
                int value = Integer.valueOf(line.substring(indexEqualSign+1));
                FoldInstruction fi = new FoldInstruction(axis, value);
                foldings.add(fi);

            }
        }
        System.out.println("done reading input");
        //p.fillWithEmptyDots();
        System.out.println("done filling with empty dots");
        //System.out.println(p);
        System.out.println();
        Paper original = p;
        p = p.apply(foldings.get(0));
        System.out.println(String.format("Nr Of dots after first fold: %d",p.nrOfDots()));
        //part 2
        //continue folding
        for(int i=1;i<foldings.size();i++){
            p=p.apply(foldings.get(i));
        }
         System.out.println(p);
    }
}
