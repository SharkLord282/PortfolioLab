package pl.coderslab.charity.Institution;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String descriptionn;

    public Institution(String name, String descriptionn) {
        this.name = name;
        this.descriptionn = descriptionn;
    }

    public Institution() {
    }

    public Institution(Long id, String name, String descriptionn) {
        this.id = id;
        this.name = name;
        this.descriptionn = descriptionn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionn() {
        return descriptionn;
    }

    public void setDescriptionn(String descriptionn) {
        this.descriptionn = descriptionn;
    }
}
