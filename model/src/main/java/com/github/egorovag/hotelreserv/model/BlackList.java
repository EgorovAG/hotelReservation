package com.github.egorovag.hotelreserv.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blackList")
public class BlackList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column(name = "date_block")
    private Date dateBlock;

    public BlackList() {
    }

    public BlackList(int id, int userId, Date dateBlock) {
        this.id = id;
        this.userId = userId;
        this.dateBlock = dateBlock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDateBlock() {
        return dateBlock;
    }

    public void setDateBlock(Date dateBlock) {
        this.dateBlock = dateBlock;
    }
}
