package com.github.egorovag.hotelreserv.dao.entity;

import com.github.egorovag.hotelreserv.model.enums.Condition;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "orderClient")
public class OrderClientEntity {

    private Integer orderId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer roomId;
    private Integer clientId;
    private Condition condition;


    private RoomEntity roomEntity;
    private ClientEntity clientEntity;
    private List<ServiceHotelEntity> serviceHotelEntities = new ArrayList<>();


    public OrderClientEntity(Integer orderId, LocalDate startDate, LocalDate endDate, Integer roomId, Integer clientId,
                             Condition condition, RoomEntity roomEntity, ClientEntity clientEntity, List<ServiceHotelEntity> serviceHotelEntities) {
        this.orderId = orderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.clientId = clientId;
        this.condition = condition;
        this.roomEntity = roomEntity;
        this.clientEntity = clientEntity;
        this.serviceHotelEntities = serviceHotelEntities;
    }

    public OrderClientEntity(Integer orderId, LocalDate startDate, LocalDate endDate, Integer roomId, Integer clientId,
                             Condition condition, RoomEntity room, ClientEntity client) {
        this.orderId = orderId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.clientId = clientId;
        this.condition = condition;
        this.roomEntity = room;
        this.clientEntity = client;
    }

    public OrderClientEntity() {
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
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Column

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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
    public RoomEntity getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(RoomEntity room) {
        this.roomEntity = room;
    }

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER )
    @JoinTable(name = "orderclient_servicehotel", joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "service_id")}
    )
    public List<ServiceHotelEntity> getServiceHotelEntities() {
        return serviceHotelEntities;
    }

    public void setServiceHotelEntities(List<ServiceHotelEntity> serviceHotelEntities) {
        this.serviceHotelEntities = serviceHotelEntities;
    }

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + orderId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", room_id=" + roomId +
                ", client_id=" + clientId +
                ", cond_id=" + condition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderClientEntity that = (OrderClientEntity) o;
        return Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
