package com.github.egorovag.hotelreserv.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name = "service")
public class Service {

    private Integer serviceId;
    private String typeOfService;
    private Integer price;


    @ManyToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<OrderClient> orderClients = new ArrayList<>();

    public Service(Integer serviceId, String typeOfService, Integer price, List<OrderClient> orderClients) {
        this.serviceId = serviceId;
        this.typeOfService = typeOfService;
        this.price = price;
        this.orderClients = orderClients;
    }

    public Service() {
    }
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return Objects.equals(serviceId, service.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId);
    }

    @Override
    public String toString() {
        return "service{" +
                "serviceId=" + serviceId +
                ", typeOfService='" + typeOfService + '\'' +
                ", price=" + price +
                '}';
    }
}
