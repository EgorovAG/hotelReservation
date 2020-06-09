package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.ServiceHotelDao;
import com.github.egorovag.hotelreserv.model.ServiceHotel;
import com.github.egorovag.hotelreserv.service.ServiceHotelService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultServiceHotelService implements ServiceHotelService {

    private final ServiceHotelDao serviceHotelDao;

    public DefaultServiceHotelService(ServiceHotelDao serviceHotelDao) {
        this.serviceHotelDao = serviceHotelDao;
    }

    @Override
    @Transactional
    public ServiceHotel readServiceByTypeOfService(String typeOfService) {
        return serviceHotelDao.readServiceByTypeOfServiceDao(typeOfService);
    }

    @Override
    @Transactional
    public boolean saveServiceListForOrder(List<ServiceHotel> serviceList, int orderId) {
        return serviceHotelDao.saveServiceListForOrderDao(serviceList, orderId);
    }

    @Override
    @Transactional
    public List<ServiceHotel> readServiceListByOrderId(int orderId) {
        return serviceHotelDao.readServiceListByOrderIdDao(orderId);
    }
}
