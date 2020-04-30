package com.github.egorovag.hotelreserv.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room")
public class Room {
    @Id
    private Integer id;
    @Column
    private Integer numOfSeats;
    @Enumerated(EnumType.STRING)
    @Column
    private ClassRoom classOfAp;
    @Column
    private Integer price;

    public Room(Integer id, Integer numOfSeats, ClassRoom classOfAp, Integer price) {
        this.id = id;
        this.numOfSeats = numOfSeats;
        this.classOfAp = classOfAp;
        this.price = price;
    }

    public Room() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(Integer numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public ClassRoom getClassOfAp() {
        return classOfAp;
    }

    public void setClassOfAp(ClassRoom classOfAp) {
        this.classOfAp = classOfAp;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

