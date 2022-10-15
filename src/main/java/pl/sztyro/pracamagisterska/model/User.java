package pl.sztyro.pracamagisterska.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @Column
    private String email;

    @Column
    private Integer incompatibilitiesCount = 0;

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

    public Integer getIncompatibilitiesCount() {
        return incompatibilitiesCount;
    }

    public void setIncompatibilitiesCount(Integer incompatibilitiesCount) {
        this.incompatibilitiesCount = incompatibilitiesCount;
    }
}
