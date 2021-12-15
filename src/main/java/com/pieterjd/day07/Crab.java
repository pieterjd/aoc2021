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
        /*
        for part 1
        return getNrOfStepChanges();
        */
        //for part 2, the sum of 1, 2, ...., getNrOfStepChanges
        int n = getNrOfStepChanges();
        return n*(n+1)/2;
    }


    public void moveToPosition(int position){
        nrOfStepChanges = Math.abs(getStartPosition()-position);
    }

    public void resetNrOfStepChanges(){
        nrOfStepChanges= 0;
    }

}
