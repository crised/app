package model;

import javax.persistence.*;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne //Owner Side (has the JOIN COLUMN)
    private Ad ad; //No tiene problemas al persistirlo debido a que no tiene @NotNull -> puede haber una imagen con un Ad no existente.

    private String description;

    private String path;

    public Picture() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
