package com.github.egorovag.hotelreserv.model;

import com.github.egorovag.hotelreserv.model.enums.Condition;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class OrderClient {

    private Integer orderId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer roomId;
    private Integer clientId;
    private Condition condition;

    private List<ServiceHotel> serviceHotels = new ArrayList<>();


    public OrderClient(Integer orderId, LocalDate startDate, LocalDate endDate, Integer roomId, Integer clientId,
                       Condition condition) {
        this.orderId = orderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.clientId = clientId;
        this.condition = condition;
    }

    public OrderClient(Integer orderId, LocalDate startDate, LocalDate endDate, Integer roomId, Integer clientId,
                       Condition condition, List<ServiceHotel> serviceHotels) {
        this.orderId = orderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.clientId = clientId;
        this.condition = condition;
        this.serviceHotels = serviceHotels;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public List<ServiceHotel> getServiceHotels() {
        return serviceHotels;
    }

    public void setServiceHotels(List<ServiceHotel> serviceHotels) {
        this.serviceHotels = serviceHotels;
    }
}
