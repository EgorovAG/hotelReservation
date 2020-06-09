package com.github.egorovag.hotelreserv.model;

import java.time.LocalDate;

public class BlackList {

    private Integer id;
    private Integer userId;
    private LocalDate dateBlock;

    public BlackList(Integer id, Integer userId, LocalDate dateBlock) {
        this.id = id;
        this.userId = userId;
        this.dateBlock = dateBlock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDate getDateBlock() {
        return dateBlock;
    }

    public void setDateBlock(LocalDate dateBlock) {
        this.dateBlock = dateBlock;
    }
}
