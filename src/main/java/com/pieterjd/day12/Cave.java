package com.pieterjd.day12;

import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
public class Cave {
    private String name;
    private boolean small;

    public Cave(String name){
        this(name, false);
    }
    public Cave(String name, boolean small) {
        this.name = name;
        this.small = small;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cave cave = (Cave) o;
        return Objects.equals(name, cave.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
