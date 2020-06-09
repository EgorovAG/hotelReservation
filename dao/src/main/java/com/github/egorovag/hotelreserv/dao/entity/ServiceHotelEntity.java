package com.github.egorovag.hotelreserv.dao.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "serviceHotel")
public class ServiceHotelEntity {

    private Integer serviceHotelId;
    private String typeOfService;
    private Integer price;


    private List<OrderClientEntity> orderClients = new ArrayList<>();

    public ServiceHotelEntity(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    public ServiceHotelEntity(Integer serviceId, String typeOfService, Integer price) {
        this.serviceHotelId = serviceId;
        this.typeOfService = typeOfService;
        this.price = price;
    }

    public ServiceHotelEntity(Integer serviceId, String typeOfService, List<OrderClientEntity> orderClients) {
        this.serviceHotelId = serviceId;
        this.typeOfService = typeOfService;
        this.orderClients = orderClients;
    }

    public ServiceHotelEntity(Integer serviceId, String typeOfService, Integer price, List<OrderClientEntity> orderClients) {
        this.serviceHotelId = serviceId;
        this.typeOfService = typeOfService;
        this.price = price;
        this.orderClients = orderClients;
    }

    public ServiceHotelEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicehotel_id")
    public Integer getServiceHotelId() {
        return serviceHotelId;
    }

    public void setServiceHotelId(Integer serviceHotelId) {
        this.serviceHotelId = serviceHotelId;
    }

    @Column
    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    @Column
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @ManyToMany(mappedBy = "services", fetch = FetchType.EAGER)
    public List<OrderClientEntity> getOrderClients() {
        return orderClients;
    }

    public void setOrderClients(List<OrderClientEntity> orderClients) {
        this.orderClients = orderClients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceHotelEntity service = (ServiceHotelEntity) o;
        return Objects.equals(serviceHotelId, service.serviceHotelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceHotelId);
    }

    @Override
    public String toString() {
        return typeOfService;
    }
}
