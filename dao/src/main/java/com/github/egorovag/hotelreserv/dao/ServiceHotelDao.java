package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Service;

import java.util.ArrayList;
import java.util.List;

public interface ServiceHotelDao {
//    List<Service> readServiceHotelList();
    Service readServiceByTypeOfServiceDao(String typeOfService);
    boolean saveServiceListForOrderDao(List<Service> serviceList, int orderId);
    List<Service> readServiceListByOrderIdDao(int orderId);

}
