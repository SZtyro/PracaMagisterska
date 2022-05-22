package pl.sztyro.pracamagisterska.model;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LearningSession {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @Column
    private Date date = new Date();

    @Type(type = "json")
    @Column(columnDefinition = "jsonb")
    private String results;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
