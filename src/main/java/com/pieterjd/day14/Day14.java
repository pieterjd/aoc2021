package com.pieterjd.day14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day14 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                ClassLoader.class.getResourceAsStream("/day14.txt")
        ));

        Template t = null;
        List<Rule> rules = new ArrayList<>();
        CounterEngine ce = new CounterEngine();
        TrackPairEngine tpe = new TrackPairEngine();
        String startText = "";
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if (line.contains(" -> ")) {
                String[] parts = line.split(" -> ");
                Rule r = new Rule(parts[0], parts[1]);
                tpe.putRule(parts[0], parts[1]);
                rules.add(r);
            } else if (!line.isEmpty()) startText = line;
        }
/* old way with string manipulation
        TemplateEngine te = new TemplateEngine(t, rules);
        for (int i = 0; i < 40; i++) {
            if(i%10==0) System.out.println(String.format("iteration %d",i));
            t = te.applyRules();
            te = new TemplateEngine(t, rules);

        }
        System.out.println(String.format("Length polymer: %d",t.getText().length()));
        String polymer = t.getText();
        Map<Character,Long> counts = new HashMap<>();
        for(int i=0;i<polymer.length();i++){
            counts.merge(polymer.charAt(i),1L,(a,b)->a+b);
        }
        System.out.println(counts);

        LongSummaryStatistics stats = counts.keySet().stream()
                .mapToLong(k -> counts.get(k))
                .summaryStatistics();
        long answer = stats.getMax()- stats.getMin();
        System.out.println("MAX-MIN = "+answer);
        //String subst =te.applyRules().getText();
        //System.out.println(subst);
        //System.out.println(new TemplateEngine(new Template(("NBBBCNCCNBBNBNBBCHBHHBCHB")), rules).applyRules().getText());
        */
        //new way with char counting
        /*
        ce.initRuleCount(startText);
        System.out.println(ce.getCharCount());
        IntStream.rangeClosed(1,40).forEach(i->ce.doIteration());
        System.out.println(ce.getCharCount());
        Map<Character, Long> counts = ce.getCharCount();
        LongSummaryStatistics stats = counts.keySet().stream()
                .mapToLong(k -> counts.get(k))
                .summaryStatistics();
        long answer = stats.getMax()- stats.getMin();
        System.out.println("max="+stats.getMax());
        System.out.println("min="+stats.getMin());
        System.out.println("MAX-MIN = "+answer);
*/
        tpe.initStuff(startText);
        IntStream.rangeClosed(1, 40).forEach(i ->
                tpe.doInteration()
        );

        {
            System.out.println(tpe.getInfo());

        }
    }
}
