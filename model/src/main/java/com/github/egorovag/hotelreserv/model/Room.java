package com.github.egorovag.hotelreserv.model;

import com.github.egorovag.hotelreserv.model.enums.ClassRoom;


public class Room {
    private Integer id;
    private Integer numOfSeats;
    private ClassRoom classOfAp;
    private Integer price;

    public Room(Integer id, Integer numOfSeats, ClassRoom classOfAp, Integer price) {
    }

    public Room(Integer id, Integer numOfSeats, ClassRoom classOfAp) {
        this.id = id;
        this.numOfSeats = numOfSeats;
        this.classOfAp = classOfAp;
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
}

