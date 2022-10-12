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

//    @OneToMany
//    private Set<BiometryChunk> biometryChunks;

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

//    public Set<BiometryChunk> getBiometryChunks() {
//        return biometryChunks;
//    }
//
//    public void setBiometryChunks(Set<BiometryChunk> biometryChunks) {
//        this.biometryChunks = biometryChunks;
//    }
}
