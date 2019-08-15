package edu.sam.aveng.base.model.transfer.dto;

import edu.sam.aveng.base.model.domain.enumeration.StatementType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CardDto {
    private Long id;

    @NotBlank(message = "{validation.card.content.blank}")
    private String content;

    @NotNull(message = "{validation.card.type.unselected}")
    private StatementType type;

    private PronunciationDto pron;

    @NotBlank(message = "{validation.card.definition.blank}")
    private String definition;

    private List<SampleDto> samples = new ArrayList<>();

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

    public PronunciationDto getPron() {
        return pron;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPron(PronunciationDto pron) {
        this.pron = pron;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<SampleDto> getSamples() {
        return samples;
    }

    public void setSamples(Collection<SampleDto> samples) {
        if (!this.samples.isEmpty()) {
            this.samples.clear();
        }
        this.samples.addAll(samples);
    }
}
