package com.pieterjd.day02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Position {
    private static final String FORWARD="forward";
    private static final String DOWN="down";
    private static final String UP="up";
    private int horizontal = 0, depth = 0;

    public Position getNext(String command, int value){
        int horizontal = getHorizontal();
        int depth = getDepth();
        if(command.equalsIgnoreCase(FORWARD)){
            horizontal += value;
        }
        if(command.equalsIgnoreCase(DOWN)){
            depth += value;
        }
        if(command.equalsIgnoreCase(UP)){
            depth -= value;
        }
        return new Position(horizontal, depth);
    }

}
