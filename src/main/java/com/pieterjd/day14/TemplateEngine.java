package com.pieterjd.day14;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Getter
public class TemplateEngine {
    private Template template;
    private List<Rule> rules;

    public Template applyRules(){
        List<Match> toApply = rules.stream()
                .map(r -> template.isRuleApplicable(r))
                .flatMap(Collection::stream)
                .filter(m->m.isMatch())
                .sorted(Comparator.comparingInt(m->-m.getIndex()))
                .collect(Collectors.toList());
        Template result = template;
        for(int i =0;i< toApply.size();i++){
            result = result.apply(toApply.get(i));
        }
        return result;
    }
}
