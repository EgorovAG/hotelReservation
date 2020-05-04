package com.github.egorovag.hotelreserv.model.dto;

import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.util.Objects;

public class OrderForAdmin {

    private Integer id;
    private String firstName;
    private String secondName;
    private String email;
    private String phone;
    private Integer clientId;
    private String startDate;
    private String endDate;
    private Condition condition;

    public OrderForAdmin(Integer id, String firstName, String secondName, String email, String phone, Integer clientId, String startDate, String endDate, Condition condition) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.clientId = clientId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.condition = condition;
    }

    public OrderForAdmin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", firstName='" + firstName +
                ", secondName='" + secondName +
                ", email='" + email +
                ", phone='" + phone +
                ", clientId=" + clientId +
                ", startDate='" + startDate +
                ", endDate='" + endDate +
                ", condition=" + condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderForAdmin that = (OrderForAdmin) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


