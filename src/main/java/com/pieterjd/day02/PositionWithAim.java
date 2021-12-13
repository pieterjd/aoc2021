package com.pieterjd.day02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PositionWithAim {
    private static final String FORWARD="forward";
    private static final String DOWN="down";
    private static final String UP="up";
    private int horizontal = 0, depth = 0, aim=0;

    public PositionWithAim getNext(String command, int value){
        int horizontal = getHorizontal();
        int depth = getDepth();
        int aim = getAim();
        if(command.equalsIgnoreCase(FORWARD)){
            horizontal += value;
            depth += aim * value;
        }
        if(command.equalsIgnoreCase(DOWN)){
            //depth += value;
            aim += value;
        }
        if(command.equalsIgnoreCase(UP)){
            //depth -= value;
            aim -= value;
        }
        return new PositionWithAim(horizontal, depth, aim);
    }

}
