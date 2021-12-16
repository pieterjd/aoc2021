package com.pieterjd.day11;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Octopus {
    private int row, col,energy;

    private boolean flashedDuringStep;

    public Octopus(int row, int col, int energy) {
        this.row = row;
        this.col = col;
        this.energy = energy;
    }

    public boolean canFlash(){
        return energy>9 && !flashedDuringStep;
    }

    public void flash(){
        energy = 0;
        flashedDuringStep = true;
    }

    public void increaseEnergy(){
        //if flashed, energy does not increase --> missing from info
       if(!flashedDuringStep) energy++;
    }
    public boolean onSamePosition(Octopus o){
        return getRow() == o.getRow() && getCol() ==o.getCol();
    }
    public void initStep(){
        flashedDuringStep = false;
    }
}
