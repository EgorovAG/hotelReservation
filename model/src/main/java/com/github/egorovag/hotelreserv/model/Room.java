package com.github.egorovag.hotelreserv.model;

import com.github.egorovag.hotelreserv.model.api.ClassRoom;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "numOfSeats")
    private int numOfSeats;
    @Enumerated(EnumType.STRING)
    @Column(name = "classOfAp")
    private ClassRoom classOfAp;
    @Column
    private int price;

    public Room(int id, int numOfSeats, ClassRoom classOfAp, int price) {
        this.id = id;
        this.numOfSeats = numOfSeats;
        this.classOfAp = classOfAp;
        this.price = price;
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public ClassRoom getClassOfAp() {
        return classOfAp;
    }

    public void setClassOfAp(ClassRoom classOfAp) {
        this.classOfAp = classOfAp;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
}

