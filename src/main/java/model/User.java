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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(unique = true)  //@Email
    private String login; //e-mail - username

    //@NotNull
    private String password; //hashed password

    //@NotNull
    private String name; //real person name

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
}
