package com.pieterjd.day08;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Builder
@EqualsAndHashCode
public class Signal {
    @Singular
    @Getter
    private Map<Character, Boolean> segments = new TreeMap<>();

    public Signal(Map<Character, Boolean> segments) {
        this.segments = segments;
    }

    public Signal(String line) {
        IntStream.range(0, line.length())
                .forEach(i -> segments.put(new Character(line.charAt(i)), Boolean.TRUE));
    }

    public boolean isSegmentOn(Character segment) {
        return segments.containsKey(segment);
    }

    public String toString() {
        return segments.keySet().stream()
                .filter(key->segments.get(key))
                .map(c -> String.valueOf(c))
                .sorted()
                .collect(Collectors.joining());
    }

    public Digit applyMapping(Map<Character,Character> mapping){
        String mapped = segments.keySet().stream()
                .filter(key->segments.get(key))
                .map(in->mapping.get(in)+"")
                .sorted()
                .collect(Collectors.joining());
       return Digit.ALL.stream()
                .filter(d->d.toString().equals(mapped))
                .findFirst()
                .orElse(null);

    }
}
