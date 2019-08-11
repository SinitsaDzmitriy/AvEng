package edu.sam.aveng.base.model.domain;

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

    /**
     * The value is used for the image description storage. It could be used to
     * search pictures by associations. This temporary attribute will be replaced
     * soon.
     * <p>
     * {@code private List<String> associations}
     * <p>
     * Contains list of  related words and phrases. That will help users to find
     * {@code Image} among already available. The owner-side should implements
     * {@code void addPicture(Image image)} to update list of association on the
     * creation of relation between objects of {@link edu.sam.aveng.base.model.domain.Picture
     * Picture} and another (owner) classes.
     * <p>
     * Known owners:
     * <ul>
     *     <li>{@link edu.sam.aveng.base.model.domain.Card Card}</li>
     * </ul>
     *
     * @see edu.sam.aveng.base.model.domain.Image
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
