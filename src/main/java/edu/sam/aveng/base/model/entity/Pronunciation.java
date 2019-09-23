package edu.sam.aveng.base.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Pronunciation implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String transcription;

    public Pronunciation() {
    }

    public Pronunciation(String transcription) {
        this.transcription = transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getTranscription() {
        return transcription;
    }

    public Long getId() {
        return id;
    }
}
