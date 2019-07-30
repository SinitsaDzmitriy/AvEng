package edu.sam.spittr.dto;

import edu.sam.spittr.domain.Pronunciation;
import edu.sam.spittr.domain.Sample;
import edu.sam.spittr.domain.enumeration.StatementType;

import java.util.HashSet;
import java.util.Set;

public class CardDto {
    private String content;
    private StatementType type;
    private Pronunciation pron;
    private String definition;
    private Set<Sample> samples = new HashSet<>();

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public StatementType getType() {
        return type;
    }

    public void setType(StatementType type) {
        this.type = type;
    }

    public Pronunciation getPron() {
        return pron;
    }

    public void setPron(Pronunciation pron) {
        this.pron = pron;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Set<Sample> getSamples() {
        return samples;
    }

    public void setSamples(Set<Sample> samples) {
        this.samples = samples;
    }
}
