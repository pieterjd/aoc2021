package com.pieterjd.day04;

import lombok.Builder;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@Builder
public class BingoGame {
    @Singular
    private List<BingoBoard> boards;
    private List<Integer> drawnNumbers;


    public BingoBoard drawNumbers(){
        List<Integer> drawNumbers = new ArrayList<>();
        this.drawnNumbers.forEach(i->drawNumbers.add(i));
        while(getWinningBoard()==null && drawnNumbers.size()>0){
            int calledNumber = drawnNumbers.get(0);
            boards.forEach(b->b.markValueOnBoard(calledNumber));
            if(getWinningBoard()==null) drawnNumbers.remove(0);
        }
        return getWinningBoard();
    }

    public List<BingoBoard> findLastCompleteBingoBoard(){
        List<BingoBoard> copyBoards = new ArrayList<>();
        boards.forEach(b->copyBoards.add(b));

        List<BingoBoard> completeBoards = new ArrayList<>();

        while(completeBoards.size() != boards.size()){
            int calledNumber = drawnNumbers.get(0);
            copyBoards.forEach(b->b.markValueOnBoard(calledNumber));
            BingoBoard bingo = getWinningBoard(copyBoards);
            if(bingo==null) drawnNumbers.remove(0);
            if(bingo!=null){
                completeBoards.add(bingo);
                copyBoards.remove(bingo);
            }
        }

        return completeBoards;
    }

    public BingoBoard getWinningBoard(){
        return getWinningBoard(boards);
    }

    private BingoBoard getWinningBoard(List<BingoBoard> boards){
        return boards.stream()
            .filter(b->b.hasBingo())
            .findFirst()
            .orElse(null);
    }

    public int getLastCalledNumber(){
        return drawnNumbers.get(0);
    }
}
