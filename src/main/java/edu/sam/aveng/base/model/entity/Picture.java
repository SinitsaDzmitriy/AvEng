package edu.sam.aveng.base.model.entity;

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
     * likeSearch pictures by associations. This temporary attribute will be replaced
     * soon.
     * <p>
     * {@code private List<String> associations}
     * <p>
     * Contains list of  related words and phrases. That will help users to likeSearch
     * {@code Image} among already available. The owner-side should implements
     * {@code void addPicture(Image image)} to update list of association on the
     * creation of relation between objects of {@link Picture
     * Picture} and another (owner) classes.
     * <p>
     * Known owners:
     * <ul>
     *     <li>{@link Card Card}</li>
     * </ul>
     *
     * @see Image
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
