package edu.sam.spittr.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "samples")
public class Sample {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    public Sample() {
    }

    public Sample(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
