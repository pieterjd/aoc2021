package com.pieterjd.day16;

import com.pieterjd.day15.Position;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Day16 {
    public static void main(String[] args) throws IOException {
        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(
            ClassLoader.class.getResourceAsStream("/day16-test.txt")
        ));

        for (String line = br.readLine(); line != null; line = br.readLine()) {

        }

         */
        Decoder d = new Decoder("D2FE28");
        System.out.println(d.hexToBin());
        System.out.println(d.getPacketVersion());
        System.out.println(d.getPacketTypeId());
        System.out.println(d.getLiteral());
    }
}
