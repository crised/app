package model;

import enums.Roles;

import javax.persistence.*;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    private String id; //e-mail - username

    //@NotNull
    private String password; //hashed password

    //@NotNull
    private String name; //real person name

    private String businessName; // Corredora

    private String phoneNumber1;

    private String phoneNumber2;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRegistered;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Ad> ad;


    public User() {
        Role roleEntity = new Role(Roles.REGISTERED);
        this.role = roleEntity;
    }


    public void storeHashPassword(String plainPassword) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(plainPassword.getBytes());
            setPassword(javax.xml.bind.DatatypeConverter.printBase64Binary(hash));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) { //cannot be accessed!
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Ad> getAd() {
        return ad;
    }

    public void setAd(List<Ad> ad) {
        this.ad = ad;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPhoneNumber1() {
        return phoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        this.phoneNumber1 = phoneNumber1;
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }
}
