package com.github.egorovag.hotelreserv.model.dto;

import com.github.egorovag.hotelreserv.model.ServiceHotel;
import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.time.LocalDate;
import java.util.Objects;

public class OrderForAdminDTO {

    private Integer id;
    private String firstName;
    private String secondName;
    private String email;
    private String phone;
    private Integer clientId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Condition condition;
    private ServiceHotel services;

    public OrderForAdminDTO() {
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public ServiceHotel getServices() {
        return services;
    }

    public void setServices(ServiceHotel services) {
        this.services = services;
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
        OrderForAdminDTO that = (OrderForAdminDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


