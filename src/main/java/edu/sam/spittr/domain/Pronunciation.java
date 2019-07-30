package edu.sam.spittr.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pronunciation {
    @Id
    @GeneratedValue
    private Long id;
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
