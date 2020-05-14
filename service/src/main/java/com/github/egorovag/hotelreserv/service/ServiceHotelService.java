package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Service;

import java.util.ArrayList;
import java.util.List;

public interface ServiceHotelService {
    Service readServiceByTypeOfService(String typeOfService);
    boolean saveServiceListForOrder(List<Service> serviceList, int orderId);
    List<Service> readServiceListByOrderId(int orderId);
}
