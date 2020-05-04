package com.github.egorovag.hotelreserv.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "blackList")
public class BlackList {

    private Integer id;
    private Integer userId;
    private Date dateBlock;

    public BlackList() {
    }

    public BlackList(Integer id, Integer userId, Date dateBlock) {
        this.id = id;
        this.userId = userId;
        this.dateBlock = dateBlock;
    }
    public BlackList(Integer userId, Date dateBlock) {
        this.userId = userId;
        this.dateBlock = dateBlock;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Column(name = "date_block")
    public Date getDateBlock() {
        return dateBlock;
    }

    public void setDateBlock(Date dateBlock) {
        this.dateBlock = dateBlock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackList blackList = (BlackList) o;
        return Objects.equals(id, blackList.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
