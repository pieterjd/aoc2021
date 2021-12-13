package com.pieterjd.day06;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class Fish {
    private int timer;

    public Fish(int timer) {
        this.timer = timer;
    }

    public Fish() {
        this(8);
    }

    public void decrease(){
        timer--;
    }
    public boolean canCreateNewFish(){
        return timer==0;
    }
    public void resetTimer(){
        timer=6;
    }
}
