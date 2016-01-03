package app.woops.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Transfer {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @NotNull
    private Integer amount;

    @Column(columnDefinition = "text")
    private String comment;

    @OneToOne
    @JoinColumn(name="fromId")
    @NotNull
    private User from;

    @OneToOne
    @JoinColumn(name = "toId")
    @NotNull
    private User to;

    public Transfer(User from, User to, Integer amount, String comment) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.comment = comment;
    }

    private Transfer() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }
}
