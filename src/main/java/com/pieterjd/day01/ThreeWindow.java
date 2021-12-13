package com.pieterjd.day01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ThreeWindow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
            ThreeWindow.class.getResourceAsStream("/day01-window.txt")
        ));
        List<Slice> ranges = new ArrayList<>();
        Slice currentSlice = new Slice();
        List<Integer> input = new ArrayList<>();
        List<Integer> sums = new ArrayList<>();
        for(String line = br.readLine(); line!=null; line = br.readLine()) {
            Integer value = Integer.parseInt(line);
            input.add(value);
        }
        for(int i = 0; i<= input.size()-3; i++){
            int sum = input.subList(i, i+3).stream().mapToInt(j->j.intValue()).sum();
            sums.add(sum);
        }

        int count=0;
        for(int i=1;i<sums.size();i++){
            if(sums.get(i)>sums.get(i-1)) count++;
        }
        System.out.println(String.format("# measurements larger than previous one: %d", count));
    }
}
