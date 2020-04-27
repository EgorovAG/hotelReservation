package com.github.egorovag.hotelreserv.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BlackListUsers {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "user_id")
    private int userId;
    @Column
    private String firstName;
    @Column
    private String secondName;
    @Column
    private Date dateBlock;

    public BlackListUsers(int id, int userId, String firstName, String secondName, Date dateBlock) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.dateBlock = dateBlock;
    }

    public BlackListUsers() {
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

    public Date getDateBlock() {
        return dateBlock;
    }

    public void setDateBlock(Date dateBlock) {
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
}
