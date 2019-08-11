package edu.sam.aveng.base.model.transfer.dto;

import java.io.Serializable;

public class SampleDto implements Serializable {

    private Long id;

    private String content;

    public SampleDto() {
    }

    public SampleDto(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
