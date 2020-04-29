package com.github.egorovag.hotelreserv.model;

public class OrderForClient {

    private int id;
    private String startDate;
    private String endDate;
    private int numOfSeats;
    private ClassRoom classOfAp;
    private int price;
    private Condition condition;


    public OrderForClient(int id, String startDate, String endDate, int numOfSeats, ClassRoom classOfAp, int price, Condition condition) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numOfSeats = numOfSeats;
        this.classOfAp = classOfAp;
        this.price = price;
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public ClassRoom getClassOfAp() {
        return classOfAp;
    }

    public int getPrice() {
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
}
