package com.pieterjd.day04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Day04 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
            ClassLoader.class.getResourceAsStream("/day04-1.txt")
        ));
        BingoGame.BingoGameBuilder bgBuilder = BingoGame.builder();
        List<Integer> drawnNumbers = new ArrayList<>();
            Arrays.asList(br.readLine().split(","))
            .stream()
            .map(s -> Integer.parseInt(s))
                .forEach(i-> drawnNumbers.add(i));
        bgBuilder.drawnNumbers(drawnNumbers);


        br.readLine();
        BingoBoard currentBoard = new BingoBoard();
        for(String line=br.readLine();line!=null;line=br.readLine()){
            if(line.length()!=0) {
                currentBoard.addRow(line);
            }
            else{
                bgBuilder.board(currentBoard);
                System.out.println(currentBoard);
                currentBoard = new BingoBoard();
            }
        }
        BingoGame game = bgBuilder.board(currentBoard).build();
        BingoBoard winner = game.drawNumbers();
        //System.out.println("----");
        //System.out.println(winner);
        int sumUnmarked = winner.sumOfUnmarkedNumbers();
        int lastCalled = winner.getLastMarkedValue();
        System.out.println(String.format("Sum of unmarked: %d, last called: %d, score: %d",sumUnmarked,lastCalled,sumUnmarked*lastCalled));
        System.out.println("---");
        List<BingoBoard> completed = game.findLastCompleteBingoBoard();
        BingoBoard lastCompleted = completed.get(completed.size()-1);
         sumUnmarked = lastCompleted.sumOfUnmarkedNumbers();
         lastCalled = lastCompleted.getLastMarkedValue();
        System.out.println(String.format("Sum of unmarked of last completed board: %d, last called: %d, score: %d",sumUnmarked,lastCalled,sumUnmarked*lastCalled));
        System.out.println();
    }
}
