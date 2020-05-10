package edu.sam.aveng.base.infrastructure.model.entity;

import edu.sam.aveng.base.contract.v2.model.Identifiable;
import edu.sam.aveng.base.infrastructure.util.TestConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "name")
public class SimpleTestEntity implements Identifiable, Serializable {

    @Id
    @GeneratedValue
    @Column(name = "name")
    private Long id;

    private String temp;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    @Column(name = "name")
    private String string;

    public SimpleTestEntity() {
    }

    public SimpleTestEntity(String string) {
        this.string = string;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleTestEntity that = (SimpleTestEntity) o;
        return string.equals(that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }
}