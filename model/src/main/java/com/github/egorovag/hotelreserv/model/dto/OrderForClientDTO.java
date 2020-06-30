package com.github.egorovag.hotelreserv.model.dto;

import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.time.LocalDate;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

public class OrderForClientDTO {

    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numOfSeats;
    private ClassRoom classOfAp;
    private Integer price;
    private Condition condition;

    public OrderForClientDTO() {
    }

    private Integer daysBetween;

    public long getDaysBetween() {
        return DAYS.between(startDate, endDate);
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
        OrderForClientDTO that = (OrderForClientDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
