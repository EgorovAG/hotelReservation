package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.ServiceHotel;

import java.util.List;

public interface ServiceHotelDao {

    ServiceHotel readServiceByTypeOfServiceDao(String typeOfService);

    boolean saveServiceListForOrderDao(List<ServiceHotel> serviceList, int orderId);

    List<ServiceHotel> readServiceListByOrderIdDao(int orderId);
}
