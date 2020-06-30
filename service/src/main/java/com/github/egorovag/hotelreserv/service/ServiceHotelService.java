package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.ServiceHotel;

import java.util.List;

public interface ServiceHotelService {

    ServiceHotel readServiceByTypeOfService(String typeOfService);

    boolean saveServiceListForOrder(List<ServiceHotel> serviceList, int orderId);

    List<ServiceHotel> readServiceListByOrderId(int orderId);
}
