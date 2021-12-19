package com.pieterjd.day14;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Rule {
    @EqualsAndHashCode.Include
    private String toMatch;
    @EqualsAndHashCode.Exclude
    @Setter
    private String replacement;

    public String getAppliedReplacement() {
        return getToMatch().substring(0, 1) + getReplacement() + getToMatch().substring(1, 2);
    }

    public char getReplacementAsChar() {
        return replacement.charAt(0);
    }

    public List<Rule> getNextRules(List<Rule> rules) {
        List<Rule> result = new ArrayList<>();

        String toMatch1 = "" + toMatch.charAt(0) + replacement;
        Rule r1 = new Rule(toMatch1, "");
        r1.setReplacement(rules.get(rules.indexOf(r1)).getReplacement());
        result.add(r1);

        String toMatch2 = "" + replacement + toMatch.charAt(1);
        Rule r2 = new Rule(toMatch2, "");
        r2.setReplacement(rules.get(rules.indexOf(r2)).getReplacement());
        result.add(r2);


        return result;
    }
}
