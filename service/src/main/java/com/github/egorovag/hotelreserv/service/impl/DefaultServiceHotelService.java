package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.ServiceHotelDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultServiceHotelDao;
import com.github.egorovag.hotelreserv.model.Service;
import com.github.egorovag.hotelreserv.service.ServiceHotelService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class DefaultServiceHotelService implements ServiceHotelService {

    private final ServiceHotelDao serviceHotelDao;

    public DefaultServiceHotelService(ServiceHotelDao serviceHotelDao) {
        this.serviceHotelDao = serviceHotelDao;
    }

    @Override
    @Transactional
    public Service readServiceByTypeOfService(String typeOfService) {
        return serviceHotelDao.readServiceByTypeOfServiceDao(typeOfService);
    }

    @Override
    @Transactional
    public boolean saveServiceListForOrder(List<Service> serviceList, int orderId) {
        return serviceHotelDao.saveServiceListForOrderDao(serviceList, orderId);
    }

    @Override
    @Transactional
    public List<Service> readServiceListByOrderId(int orderId) {
        return serviceHotelDao.readServiceListByOrderIdDao(orderId);
    }
}
