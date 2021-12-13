package com.pieterjd.day04;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class BingoBoard {
    public static final int SIZE=5;
    @Singular
    private List<BingoPosition> positions = new ArrayList<>();
    @Getter
    private boolean bingo = false;
    private List<Integer> drawnNumbers = new ArrayList<>();
    public void addRow(String row){
        List<Integer> numbers = Arrays.stream(row.split("\\s+"))
            .filter(s->s.length()>0)
            .map(s -> Integer.parseInt(s))
            .collect(Collectors.toList());
        addRow(numbers);
    }

    public void addRow(List<Integer> rowValues){
        int row = getNextRow();
        for(int i=0;i<rowValues.size();i++){
            int col = i;
            int value = rowValues.get(i);
            BingoPosition bp = BingoPosition.builder()
                .row(row)
                .col(col)
                .value(value)
                .build();
            positions.add(bp);

        }
    }

    private int getNextRow() {
        return positions.size() / SIZE;
    }

    public void markValueOnBoard(int value){
        drawnNumbers.add(value);
        positions.stream()
            .filter(pos->pos.getValue() == value)
            .forEach(pos->pos.setMarked(true));
    }

    public int getLastMarkedValue(){
        return drawnNumbers.get(drawnNumbers.size()-1);
    }

    public boolean hasBingo(){
        return hasRowBingo() || hasColBingo();
    }

    private boolean hasColBingo(){
        return IntStream.range(0, SIZE)
            .anyMatch(col -> hasColBingo(col));
    }
    private boolean hasColBingo(int col) {
        return positions.stream()
            .filter(pos->pos.getCol()==col)
            .allMatch(pos->pos.isMarked());
    }
    private boolean hasRowBingo(){
        return IntStream.range(0, SIZE)
            .anyMatch(row -> hasRowBingo(row));
    }
    private boolean hasRowBingo(int row) {
        return positions.stream()
            .filter(pos->pos.getRow()==row)
            .allMatch(pos->pos.isMarked());
    }

    private String getRowString(int row){
        return positions.stream()
            .filter((p->p.getRow()==row))
            .map(p->""+p.getValue())
            .collect(Collectors.joining(" "));
    }

    public String toString(){
        return IntStream.range(0,SIZE)
            .mapToObj(i -> getRowString(i))
            .collect(Collectors.joining("\n"));
    }
    public int sumOfUnmarkedNumbers(){
        return positions.stream()
            .filter(p->!p.isMarked())
            .mapToInt(p->p.getValue())
            .sum();
    }
}
