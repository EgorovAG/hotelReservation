package com.github.egorovag.hotelreserv.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orderClient")
public class OrderClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String startDate;
    @Column
    private String endDate;
    @Column(name = "room_id")
    private Integer roomId;
    @Column (name = "client_id")
    private Integer userId;
    @Enumerated(EnumType.STRING)
    @Column(name = "conditions")
    private Condition condition;

    public OrderClient(String startDate, String endDate, Integer roomId, Condition condition) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.condition = condition;
    }

    public OrderClient(String startDate, String endDate, Integer roomId, Integer userId, Condition condition) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.userId = userId;
        this.condition = condition;
    }


    public OrderClient(Integer id, String startDate, String endDate, Integer roomId, Integer userId, Condition condition) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.userId = userId;
        this.condition = condition;
    }

    public OrderClient() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderClient that = (OrderClient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
