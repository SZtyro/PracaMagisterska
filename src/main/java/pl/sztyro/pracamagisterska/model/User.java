package pl.sztyro.pracamagisterska.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "service_user")
public class User {

    @Id
    @Column
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String email) {
        this.email = email;
    }

    public User() {
    }
}
