package br.com.labrador.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Loans.
 */
@Entity
@Table(name = "loans")
public class Loans implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "loan_date")
    private ZonedDateTime loanDate;

    @Column(name = "return_date")
    private ZonedDateTime returnDate;

    @OneToOne
    @JoinColumn(unique = true)
    private Bone bone;

    @ManyToOne
    private User taker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getLoanDate() {
        return loanDate;
    }

    public Loans loanDate(ZonedDateTime loanDate) {
        this.loanDate = loanDate;
        return this;
    }

    public void setLoanDate(ZonedDateTime loanDate) {
        this.loanDate = loanDate;
    }

    public ZonedDateTime getReturnDate() {
        return returnDate;
    }

    public Loans returnDate(ZonedDateTime returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public void setReturnDate(ZonedDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Bone getBone() {
        return bone;
    }

    public Loans bone(Bone bone) {
        this.bone = bone;
        return this;
    }

    public void setBone(Bone bone) {
        this.bone = bone;
    }

    public User getTaker() {
        return taker;
    }

    public Loans taker(User user) {
        this.taker = user;
        return this;
    }

    public void setTaker(User user) {
        this.taker = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Loans loans = (Loans) o;
        if (loans.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, loans.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Loans{" +
            "id=" + id +
            ", loanDate='" + loanDate + "'" +
            ", returnDate='" + returnDate + "'" +
            '}';
    }
}
