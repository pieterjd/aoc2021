package com.pieterjd.day14;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Template {
    private String text;

    public Template(String text) {
        this.text = text;
    }

    public List<Match> isRuleApplicable(Rule r) {
        List<Match> matches = new ArrayList<>();
        int startIndex = 0;
        int matchIndex = text.indexOf(r.getToMatch(), startIndex);
        while (matchIndex != -1) {
            Match.MatchBuilder builder = Match.builder().rule(r);
            builder.index(matchIndex);
            matches.add(builder.build());
            startIndex = matchIndex + 1;
            matchIndex = text.indexOf(r.getToMatch(), startIndex);
        }
        return matches;
    }

    public Template apply(Match m) {
        /*
        String replacement = m.getRule().getAppliedReplacement();
        String upTillIndex = text.substring(0,m.getIndex()+offset);
        String lastPart = text.substring(m.getIndex()+offset+m.getRule().getToMatch().length());
        String newText = upTillIndex+replacement+lastPart;
*/
        String replacement = m.getRule().getReplacement();
        int indexMatch = m.getIndex();
        String lastPart = text.substring(indexMatch + m.getRule().getToMatch().length());
        String firstPart = text.substring(0, indexMatch);
        String newText = firstPart
                + m.getRule().getToMatch().charAt(0)
                + replacement
                + m.getRule().getToMatch().charAt(1)
                + lastPart;

        return new Template(newText);
    }

}
