package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.impl.DefaultServiceHotelDao;
import com.github.egorovag.hotelreserv.model.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ServiceHotelDaoTest {
    ServiceHotelDao serviceDao = DefaultServiceHotelDao.getInstance();

    @Test
    void testReadServiceHotelList() {
        List<Service> serviceHotelList = serviceDao.readServiceHotelList();
        Assertions.assertFalse(serviceHotelList.isEmpty());
    }
}