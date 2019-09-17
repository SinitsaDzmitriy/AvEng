package edu.sam.aveng.base.model.domain;

import edu.sam.aveng.base.contract.model.Identifiable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

@Entity
@Table(name = "samples",
        uniqueConstraints =
        @UniqueConstraint(columnNames = "content", name = "uq_content"))
public class Sample implements Serializable, Identifiable {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
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



    @Override
    public int hashCode() {
        return content.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sample sample = (Sample) obj;
        return this.content.equals(sample.content);
    }
}
