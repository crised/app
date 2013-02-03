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

    private BigDecimal price;   // min 2000000

    private Boolean removed;

    private Boolean waterRights; // Whether haves water rights registered

    private Boolean facilities; // Whether haves constructions

    private float latitude;  // Positive for N and E, negative for S and W


    private float longitude;

    private float surface; // Ha. (Acre) Only one decimal allowed. Min 2.


    @Version
    private long version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;

    @Enumerated(EnumType.STRING)
    private City city;

    @OneToMany(mappedBy = "ad", fetch = FetchType.EAGER)
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

    public long getVersion() {
        return version;
    }

    protected void setVersion(long version) {     //not supposed to be called
        this.version = version;
    }

    public Boolean getRemoved() {
        return removed;
    }

    public void setRemoved(Boolean removed) {
        this.removed = removed;
    }

    public Boolean getWaterRights() {
        return waterRights;
    }

    public void setWaterRights(Boolean waterRights) {
        this.waterRights = waterRights;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Boolean getFacilities() {
        return facilities;
    }

    public void setFacilities(Boolean facilities) {
        this.facilities = facilities;
    }

    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }
}
