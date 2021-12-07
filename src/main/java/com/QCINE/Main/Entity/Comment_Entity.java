package com.QCINE.Main.Entity;

import com.QCINE.Main.Entity.Embedded.CommentId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="comment")
public class Comment_Entity {

    @EmbeddedId
    private CommentId id;

    @Column(length = 100)
    private String cmt;

    @Max(5)
    @Min(0)
    private int rating;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPhim")
    @JoinColumn(name = "idphim")
    private Phim_Entity phim;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idCustomer")
    @JoinColumn(name = "idcustomer")
    private Customer_Entity customer;


    public CommentId getId() {
        return id;
    }

    public void setId(CommentId id) {
        this.id = id;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Phim_Entity getPhim() {
        return phim;
    }

    public void setPhim(Phim_Entity phim) {
        this.phim = phim;
    }

    public Customer_Entity getCustomer() {
        return customer;
    }

    public void setCustomer(Customer_Entity customer) {
        this.customer = customer;
    }
}
