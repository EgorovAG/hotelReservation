package com.github.egorovag.hotelreserv.dao.entity;

import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "room")
public class RoomEntity {
    private Integer id;
    private Integer numOfSeats;
    private ClassRoom classOfAp;
    private Integer price;

    private OrderClientEntity orderClientEntity;

    public RoomEntity(Integer id, Integer numOfSeats, ClassRoom classOfAp) {
        this.id = id;
        this.numOfSeats = numOfSeats;
        this.classOfAp = classOfAp;
    }

    public RoomEntity(Integer id, Integer numOfSeats, ClassRoom classOfAp, Integer price, OrderClientEntity orderClientEntity) {
        this.id = id;
        this.numOfSeats = numOfSeats;
        this.classOfAp = classOfAp;
        this.price = price;
        this.orderClientEntity = orderClientEntity;
    }

    public RoomEntity(Integer id, Integer numOfSeats, ClassRoom classOfAp, OrderClientEntity orderClientEntity) {
        this.id = id;
        this.numOfSeats = numOfSeats;
        this.classOfAp = classOfAp;
        this.orderClientEntity = orderClientEntity;
    }

    public RoomEntity() {
    }

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(Integer numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    @Enumerated(EnumType.STRING)
    @Column
    public ClassRoom getClassOfAp() {
        return classOfAp;
    }

    public void setClassOfAp(ClassRoom classOfAp) {
        this.classOfAp = classOfAp;
    }

    @Column
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @OneToOne(mappedBy = "roomEntity", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    public OrderClientEntity getOrderClientEntity() {
        return orderClientEntity;
    }

    public void setOrderClientEntity(OrderClientEntity orderClientEntity) {
        this.orderClientEntity = orderClientEntity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numOfSeats=" + numOfSeats +
                ", classOfAp=" + classOfAp +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomEntity room = (RoomEntity) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

