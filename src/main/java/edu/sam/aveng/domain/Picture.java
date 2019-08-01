package edu.sam.aveng.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "pictures")
public class Picture implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    /*
    Temporary field, it will be replaced with

    private List<String> associations;

    Contain list of associations (words and phrases related to the bound
image). It will help user to find image for a new card among already available.
The card-side void addPicture(Image picture) method should also update
association list, add a new one.
    */

    private String description;

    public Picture() {
    }

    public Picture(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
