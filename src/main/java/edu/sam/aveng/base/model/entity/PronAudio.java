package edu.sam.aveng.base.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Blob;

@Entity
@Table(name = "pron_media")
public class PronAudio {
    @Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Pronunciation pron;

    @Lob
    private Blob audio;

    public PronAudio() {
    }

    public PronAudio(
            Pronunciation pron,
            Blob audio) {
        this.pron = pron;
        this.audio = audio;
    }

    public Blob getAudio() {
        return audio;
    }
}
