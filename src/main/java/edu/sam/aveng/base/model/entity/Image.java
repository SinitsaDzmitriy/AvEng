package edu.sam.aveng.base.model.entity;

import org.apache.commons.lang3.SerializationUtils;
import org.hibernate.engine.jdbc.BlobProxy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name = "images")
public class Image implements Serializable {
    @Id
    private Long id;

    @Lob
    private Blob content;

    @OneToOne(cascade = CascadeType.PERSIST)
    @MapsId
    private Picture picture;

    public Image() {
        content = BlobProxy.generateProxy(SerializationUtils.serialize("data"));
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