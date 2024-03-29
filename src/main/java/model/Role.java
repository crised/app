package model;

import enums.Roles;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;


    //@NotNull
    @Enumerated(EnumType.STRING)
    private Roles role;

    public Role() {
    }

    public Role(Roles roles) {
        setRole(roles);
    }


    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
