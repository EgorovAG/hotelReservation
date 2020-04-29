package com.github.egorovag.hotelreserv.model;

import javax.persistence.*;

@Entity
@Table(name = "orderClient")
public class OrderClient {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String startDate;
    @Column
    private String endDate;
    @Column(name = "room_id")
    private int roomId;
    @Column (name = "client_id")
    private int userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "conditions")
    private Condition condition;

    public OrderClient(String startDate, String endDate, int roomId, Condition condition) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.condition = condition;
    }

    public OrderClient(String startDate, String endDate, int roomId, int userId, Condition condition) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.userId = userId;
        this.condition = condition;
    }


    public OrderClient(int id, String startDate, String endDate, int roomId, int userId, Condition condition) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.userId = userId;
        this.condition = condition;
    }

    public OrderClient() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", room_id=" + roomId +
                ", user_id=" + userId +
                ", cond_id=" + condition +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
