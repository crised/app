package model;

import enums.City;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Ad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String shortDescription;

    private String longDescription;

    private BigDecimal price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;

    @Enumerated(EnumType.STRING)
    private City city;

    @OneToMany(mappedBy = "ad")
    private List<Picture> pictureList;

    @ManyToOne
    private User user;

    public Ad() {
    }

    public Ad(String shortDescription, String longDescription, BigDecimal price, City city) {
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.price = price;
        this.city = city;

    }

    @PostConstruct
    public void init() {

        setDateRegistered(new Date());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Picture> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
}
