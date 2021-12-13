package com.pieterjd.day07;

import lombok.Getter;
import lombok.Setter;

public class Crab {
    @Getter
    private int startPosition;
    @Getter
    @Setter
    private int nrOfStepChanges;

    public Crab(int startPosition, int nrOfStepChanges) {
        this.startPosition = startPosition;
        this.nrOfStepChanges = nrOfStepChanges;
    }
    public Crab(int startPosition) {
       this(startPosition, 0);
    }

    public int getFuelCost(){
        return getNrOfStepChanges();
    }

    public void moveToPosition(int position){
        nrOfStepChanges = Math.abs(getStartPosition()-position);
    }

    public void resetNrOfStepChanges(){
        nrOfStepChanges= 0;
    }

}
