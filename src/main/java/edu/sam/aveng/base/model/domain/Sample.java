package edu.sam.aveng.base.model.domain;

import edu.sam.aveng.base.contract.model.Identifiable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "samples")
public class Sample implements Serializable, Identifiable {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}