package pl.sztyro.pracamagisterska.model;

import javax.persistence.*;

@Table
@Entity
public class MembershipFunction {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Double a,b,c;

    public MembershipFunction() {
    }

    public MembershipFunction(Double a, Double b, Double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "MembershipFunction{" +
                "id=" + id +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getA() {
        return a;
    }

    public void setA(Double a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }
}
