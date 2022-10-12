package pl.sztyro.pracamagisterska.model;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
public class BiometryChunk implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    //@JoinColumn(name="app_user_email", nullable=false)
    private User appUser;

    @Column
    private String pair;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "membership_function_id", referencedColumnName = "id")
    private MembershipFunction membershipFunction;

    @Type(type = "list-array")
    @Column(
            columnDefinition = "text[]"
    )
    private List<String> times;

    public BiometryChunk() {
    }

    public BiometryChunk(User appUser, String pair, List<String> times) {
        this.appUser = appUser;
        this.pair = pair;
        this.times = times;
    }

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    public User getAppUser() {
        return appUser;
    }

    public void setAppUser(User appUser) {
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MembershipFunction getMembershipFunction() {
        return membershipFunction;
    }

    public void setMembershipFunction(MembershipFunction membershipFunction) {
        this.membershipFunction = membershipFunction;
    }
}
