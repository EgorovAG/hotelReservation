package com.github.egorovag.hotelreserv.model;

import com.github.egorovag.hotelreserv.model.enums.Condition;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "orderClient")
public class OrderClient {

    private Integer orderId;
    private String startDate;
    private String endDate;
    private Integer roomId;
    private Integer userId;
    private Condition condition;


    private Room room;
    private Client client;


    private List<Service> services = new ArrayList<>();


    public OrderClient(Integer orderId, String startDate, String endDate, Integer roomId,
                       Integer userId, Condition condition, Room room, Client client) {
        this.orderId = orderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.userId = userId;
        this.condition = condition;
        this.room = room;
        this.client = client;
    }

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


    public OrderClient(Integer orderId, String startDate, String endDate, Integer roomId, Integer userId, Condition condition) {
        this.orderId = orderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.userId = userId;
        this.condition = condition;
    }

    public OrderClient() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Column
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Column

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Column(name = "room_id", insertable = false, updatable = false)

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Column(name = "client_id", insertable = false, updatable = false)

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "conditions")
    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "orderClient_service", joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "user_id")
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + orderId +
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
        return Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
