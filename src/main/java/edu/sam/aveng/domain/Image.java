package edu.sam.aveng.domain;

import org.hibernate.engine.jdbc.BlobProxy;

import javax.persistence.*;
import java.sql.Blob;

// ToDo: Decide what we should do when admin deletes card.
/*
        Thoughts
        One picture to many cards.
*/

@Entity
@Table(name = "images")
public class Image {
    @Id
    private Long id;

    @Lob
    private Blob content;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    private Picture picture;

    public Image() {
    }

    /*
        ToDo: Handle exception!

        throws IllegalArgumentException
        if (image == null) {
            throw new IllegalArgumentException();
        }
    */

    public Image(Picture picture, byte[] content) {
        this.picture = picture;
        this.content = BlobProxy.generateProxy(content);
    }

    public Long getId() {
        return id;
    }

    public Blob getContent() {
        return content;
    }
}