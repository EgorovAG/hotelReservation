package com.github.egorovag.hotelreserv.model;

public class ServiceHotel {

    private Integer serviceHotelId;
    private String typeOfService;
    private Integer price;

    public ServiceHotel(Integer serviceHotelId, String typeOfService, Integer price) {
        this.serviceHotelId = serviceHotelId;
        this.typeOfService = typeOfService;
        this.price = price;
    }

    public Integer getServiceHotelId() {
        return serviceHotelId;
    }

    public void setServiceHotelId(Integer serviceHotelId) {
        this.serviceHotelId = serviceHotelId;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
