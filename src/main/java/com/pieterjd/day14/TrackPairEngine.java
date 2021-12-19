package com.pieterjd.day14;

import java.util.HashMap;
import java.util.LongSummaryStatistics;
import java.util.Map;

public class TrackPairEngine {
    //based on https://skarlso.github.io/2021/12/14/aoc-day14/
    //because I just want to get over it :(
    private Map<String, String> rules = new HashMap<>();
    private Map<String, Long> trackPairs = new HashMap<>();
    private Map<String, Long> charCounts = new HashMap<>();
    private String text;

    public void initStuff(String text) {
        this.text = text;
        for (int i = 0; i < text.length() - 1; i++) {
            trackPairs.merge("" + text.charAt(i) + text.charAt(i + 1), 1L, (a, b) -> a + b);
            charCounts.merge("" + text.charAt(i), 1l, (a, b) -> a + b);
        }
        charCounts.merge("" + text.charAt(text.length() - 1), 1l, (a, b) -> a + b);
    }

    public String putRule(String key, String value) {
        return rules.put(key, value);
    }

    public void doInteration() {
        Map<String, Long> update = new HashMap<>();
        trackPairs.forEach((k, v) -> {
            update.merge(k.charAt(0) + rules.get(k), v, (a, b) -> a + b);
            update.merge(rules.get(k) + k.charAt(1), v, (a, b) -> a + b);

        });
        Map<String, Long> counts = new HashMap<>();
        trackPairs.forEach((k, v) -> {
            counts.merge(rules.get(k), v, (a, b) -> a + b);
        });

        counts.forEach((k, v) -> {
            charCounts.merge(k, v, (a, b) -> a + b);
        });

        trackPairs = update;
    }

    public String getInfo() {

        LongSummaryStatistics stats = charCounts.keySet().stream()
                .mapToLong(k -> charCounts.get(k))
                .summaryStatistics();
        long answer = stats.getMax() - stats.getMin();
        StringBuilder sb = new StringBuilder();
        sb.append("max=" + stats.getMax());sb.append("\n");
        sb.append("min=" + stats.getMin());sb.append("\n");
        sb.append("MAX-MIN = " + answer);sb.append("\n");
        return sb.toString();
    }


}
