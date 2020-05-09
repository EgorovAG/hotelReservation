package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.ServiceDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultServiceDao;
import com.github.egorovag.hotelreserv.model.Service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ServiceDaoTest {
    ServiceDao serviceDao = DefaultServiceDao.getInstance();

    @Test
    void testLoadService() {
        List<Service> serviceList = serviceDao.readService();
        Assertions.assertFalse(serviceList.isEmpty());

    }
}