package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.ServiceHotelDao;
import com.github.egorovag.hotelreserv.model.Service;
import com.github.egorovag.hotelreserv.service.impl.DefaultServiceHotelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServiceHotelServiceTest {

    @Mock
    ServiceHotelDao serviceHotelDao;

    @InjectMocks
    DefaultServiceHotelService defaultServiceHotelService;

    private Service service = new Service(1, "pool", 50);

    @Test
    void readServiceByTypeOfService() {
        when(serviceHotelDao.readServiceByTypeOfServiceDao(service.getTypeOfService())).thenReturn(service);
        Service serviceRes = defaultServiceHotelService.readServiceByTypeOfService(service.getTypeOfService());
        Assertions.assertEquals(service, serviceRes);
    }

    @Test
    void saveServiceListForOrder() {
        List<Service> serviceList = new ArrayList<>();
        when(serviceHotelDao.saveServiceListForOrderDao(serviceList, 10)).thenReturn(true);
        boolean res = defaultServiceHotelService.saveServiceListForOrder(serviceList, 10);
        Assertions.assertTrue(res);
    }

    @Test
    void readServiceListByOrderId() {
        List<Service> serviceList = new ArrayList<>();
        when(serviceHotelDao.readServiceListByOrderIdDao(10)).thenReturn(serviceList);
        List<Service> serviceListRes = serviceHotelDao.readServiceListByOrderIdDao(10);
        Assertions.assertEquals(serviceList, serviceListRes);
    }
}