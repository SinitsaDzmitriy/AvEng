package edu.sam.aveng.dto;

public class SampleDto {

    private long id;

    private String content;

    public SampleDto() {
    }

    public SampleDto(String content) {
        this.content = content;
    }

    public long getId() {
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
