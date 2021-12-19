package com.pieterjd.day14;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class CounterEngine {
    private Map<String, String> rules = new HashMap<>();
    private Map<String, Long> ruleCount = new HashMap<>();
    @Getter
    private Map<Character, Long> charCount = new HashMap<>();

    public String putRule(String key, String value) {
        return rules.put(key, value);
    }

    public void initRuleCount(String text) {
        for (String rule :
                rules.keySet()) {
            int index = text.indexOf(rule);
            if (index > -1) {
                ruleCount.merge(rule, 1L, (a, b) -> a + b);
            }
        }

        for (int i = 0; i < text.length(); i++) {
            charCount.merge(text.charAt(i), 1L, (a, b) -> a + b);
        }

    }

    public void doIteration() {
        //add new chars of current rules
        //add to existing charcount
        Map<Character, Long> charsToAddCount = new HashMap<>();
        ruleCount.forEach((rule, nr) -> {
                    Character toAdd = rules.get(rule).charAt(0);
                    long nrToAdd = nr;
                    charsToAddCount.merge(toAdd,nrToAdd,(a,b)->a+b);
                }

        );
        /*
        List<Character> charsToAdd = ruleCount.keySet().stream()
                .map(s -> rules.get(s))
                .map(s -> s.charAt(0))
                .collect(Collectors.toList());
        Map<Character, Integer> charsToAddCount = charsToAdd.stream().collect(Collectors.toMap(
                c -> new Character(c), c -> 1, (a, b) -> a + b
        ));

         */

        //charsToAdd.forEach(c->charCount.merge(c, ruleCount.getOrDefault(c,1), (a, b) -> a + b));
        charsToAddCount.keySet().forEach(bla -> charCount.merge(
                        bla,
                        charsToAddCount.getOrDefault(bla, 1L),
                        (a, b) -> a + b
                )
        );


        //generate the nuew rule
        Map<String, Long> ruleCountUpdated = new HashMap<>();
        ruleCount.forEach((key, value) -> {
            String r1 = key.charAt(0) + rules.get(key);
            if(rules.containsKey(r1)) ruleCountUpdated.merge(r1, value, (a, b) -> a + b);
            String r2 = rules.get(key) + key.charAt(1);
            if(rules.containsKey(r2)) ruleCountUpdated.merge(r2, value, (a, b) -> a + b);
            //System.out.println(String.format("Rule %s generated %s and %s", key, r1, r2));
        });


        //update ruleCount
        ruleCount = ruleCountUpdated;

    }
}
