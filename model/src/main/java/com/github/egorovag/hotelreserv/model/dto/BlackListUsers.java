package com.github.egorovag.hotelreserv.model.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;


public class BlackListUsers {
    private Integer id;
    private Integer userId;
    private String firstName;
    private String secondName;
    private LocalDate dateBlock;

    public BlackListUsers(Integer id, int userId, String firstName, String secondName, LocalDate dateBlock) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateBlock = dateBlock;
    }

    public BlackListUsers(Integer id, Integer userId, String firstName, String secondName) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public BlackListUsers() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getDateBlock() {
        return dateBlock;
    }

    public void setDateBlock(LocalDate dateBlock) {
        this.dateBlock = dateBlock;
    }

    @Override
    public String toString() {
        return "BlackListUsers{" +
                "id=" + id +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", dateBlock=" + dateBlock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackListUsers that = (BlackListUsers) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
