package edu.sam.aveng.base.model.transfer.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class SampleDto implements Serializable {

    private Long id;

    @NotBlank(message = "{validation.sample.content.blank}")
    private String content;

    public SampleDto() {}

    public SampleDto(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
