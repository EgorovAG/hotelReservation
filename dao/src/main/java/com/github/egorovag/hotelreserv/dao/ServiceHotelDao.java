package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.Service;

import java.util.List;

public interface ServiceHotelDao {
    List<Service> readServiceHotelList();
    Service readServiceByTypeOfService(String typeOfService);

}
