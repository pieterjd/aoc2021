package com.pieterjd.day10;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LineParser {
    private List<Character> lefties = Arrays.asList('(','[','{','<');
    private List<Character> righties = Arrays.asList(')',']','}','>');
    private Map<Character, Character> left2right = new HashMap<>();

    public LineParser() {
        IntStream.range(0, lefties.size())
                .forEach(i->left2right.put(lefties.get(i), righties.get(i)));
    }

    public ParseInformation parseLine(String line){
        ParseInformation.ParseInformationBuilder piBuilder = ParseInformation.builder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            Character c = line.charAt(i);
            if(lefties.contains(c)){
                stack.push(c);
            }
            else{
                //should be a right bracket
                Character rightChar = c;
                if(stack.empty()) {
                    return piBuilder.success(false)
                            .stackEmpty(stack.empty())
                            .position(i)
                            .build();
                }
                if(righties.contains(rightChar)) {
                    Character lastLeftChar = stack.peek();
                    Character expectedRightChar = left2right.get(lastLeftChar);
                    if (expectedRightChar.equals(rightChar)) {
                        //all ok
                        stack.pop();
                    }
                    else{
                        return piBuilder
                                .expected(expectedRightChar)
                                .stackEmpty(stack.empty())
                                .found(rightChar)
                                .success(false)
                                .position(i)
                                .build();
                    }
                }
            }
        }
        return piBuilder.success(true)
                .stackEmpty(stack.empty())
                .completion(getClosingBrackets(stack))
                .build();
    }

    private String getClosingBrackets(Stack<Character> stack){
        String result = "";
        while(!stack.isEmpty()){
            Character c = stack.pop();
            result +=left2right.get(c);
        }
        return result;
    }
}
