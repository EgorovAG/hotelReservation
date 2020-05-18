package com.github.egorovag.hotelreserv.model.dto;

import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.time.LocalDate;
import java.util.Objects;

public class OrderForClient {

    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numOfSeats;
    private ClassRoom classOfAp;
    private Integer price;
    private Condition condition;


    public OrderForClient(Integer id, LocalDate startDate, LocalDate endDate, Integer numOfSeats, ClassRoom classOfAp, Integer price, Condition condition) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numOfSeats = numOfSeats;
        this.classOfAp = classOfAp;
        this.price = price;
        this.condition = condition;
    }

    public OrderForClient() {
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public ClassRoom getClassOfAp() {
        return classOfAp;
    }

    public Integer getPrice() {
        return price;
    }

    public Condition getCondition() {
        return condition;
    }

    @Override
    public String toString() {
        return "OrderForClient{" +
                "id=" + id +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", numOfSeats=" + numOfSeats +
                ", classOfAp=" + classOfAp +
                ", price=" + price +
                ", condition=" + condition +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderForClient that = (OrderForClient) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
