package model;

import enums.City;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class Ad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Size(min = 10, max = 50, message = "{ad.shortDescription.size}")
    @NotNull
    private String shortDescription;

    @Size(min = 100, max = 5_000, message = "{ad.shortDescription.size}")
    @NotNull
    private String longDescription;

    @Max(value = 10_000_000_000L, message = "{ad.price.max}")
    @Min(value = 5_000_000, message = "{ad.price.min}")
    @Digits(integer = 11, fraction = 0, message = "{ad.price.digits}")
    @NotNull
    private BigDecimal price;   // min 2000000

    @Enumerated(EnumType.STRING)
    @NotNull
    private City city;

    @Min(value = 2, message = "{ad.surface.min}")
    @Digits(integer = 5, fraction = 1, message = "{ad.surface.digits}")
    @Max(value = 9999, message = "{ad.surface.max")
    @NotNull
    private float surface; // Ha. (Acre) Only one decimal allowed. Min 2.

    @NotNull
    private Boolean waterRights; // Whether haves water rights registered

    @NotNull
    private Boolean facilities; // Whether haves constructions

    @NotNull
    private double latitude;  // Positive for N and E, negative for S and W

    @NotNull
    private double longitude;

    /* Automatic or Not Needed */

    private Boolean removed;

    @Version
    private long version;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;



    @OneToMany(mappedBy = "ad", fetch = FetchType.EAGER)
    private List<Picture> pictureList;

    @ManyToOne
    private User user;

    public Ad() {
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
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
