package edu.sam.aveng.base.infrastructure.model.entity;

import edu.sam.aveng.base.contract.model.Identifiable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "compose_entities")
public class ComposeTestEntity implements Identifiable, Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private double primitiveProperty;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "simple_entity_id", unique = true,
            foreignKey = @ForeignKey(name = "SIMPLE_ENTITY_ID_FK"))
    private SimpleTestEntity objProperty;

    public ComposeTestEntity() {
    }

    public ComposeTestEntity(double primitiveProperty, SimpleTestEntity objProperty) {
        this.primitiveProperty = primitiveProperty;
        this.objProperty = objProperty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrimitiveProperty() {
        return primitiveProperty;
    }

    public void setPrimitiveProperty(double primitiveProperty) {
        this.primitiveProperty = primitiveProperty;
    }

    public SimpleTestEntity getObjProperty() {
        return objProperty;
    }

    public void setObjProperty(SimpleTestEntity objProperty) {
        this.objProperty = objProperty;
    }
}
