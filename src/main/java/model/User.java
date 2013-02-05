package model;

import enums.Roles;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
    message = "{user.emailNotValid}")
    private String id; //e-mail - username

    @NotEmpty
    private String password; //hashed password

    @NotEmpty
    @Size(min = 3, max = 50, message = "{user.nameTooLong}")
    private String name; //real person name

    private Boolean isMailConfirmed;

    @NotEmpty(message = "{user.BusinessNameBlank}")
    @Size(min = 2,max=20,message = "{user.BusinessName}")
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


    public static String getHashPassword(String plainPassword) {

        MessageDigest md = null;
        String stringHash = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(plainPassword.getBytes());
            stringHash = javax.xml.bind.DatatypeConverter.printBase64Binary(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringHash;

    }

    public void storeHashPassword(String plainPassword) {

        setPassword(getHashPassword(plainPassword));

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

    public Boolean getMailConfirmed() {
        return isMailConfirmed;
    }

    public void setMailConfirmed(Boolean mailConfirmed) {
        isMailConfirmed = mailConfirmed;
    }
}
