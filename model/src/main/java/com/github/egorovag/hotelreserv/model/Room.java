package com.github.egorovag.hotelreserv.model;

import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "room")
public class Room {
    private Integer id;
    private Integer numOfSeats;
    private ClassRoom classOfAp;
    private Integer price;

    private OrderClient orderClient;

    public Room(Integer id, Integer numOfSeats, ClassRoom classOfAp, Integer price) {
        this.id = id;
        this.numOfSeats = numOfSeats;
        this.classOfAp = classOfAp;
        this.price = price;
    }

    public Room() {
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

    @OneToOne(mappedBy = "room", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public OrderClient getOrderClient() {
        return orderClient;
    }

    public void setOrderClient(OrderClient orderClient) {
        this.orderClient = orderClient;
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
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

