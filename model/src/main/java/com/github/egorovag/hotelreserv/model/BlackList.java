package com.github.egorovag.hotelreserv.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "blackList")
public class BlackList {

    private Integer id;
    private Integer userId;
    private LocalDate dateBlock;

    private AuthUser authUser;

    public BlackList() {
    }

    public BlackList(Integer id, Integer userId, LocalDate dateBlock) {
        this.id = id;
        this.userId = userId;
        this.dateBlock = dateBlock;
    }

    public BlackList(Integer userId, LocalDate dateBlock) {
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

    @Column(name = "user_id", insertable = false, updatable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "date_block")
    public LocalDate getDateBlock() {
        return dateBlock;
    }

    public void setDateBlock(LocalDate dateBlock) {
        this.dateBlock = dateBlock;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
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
