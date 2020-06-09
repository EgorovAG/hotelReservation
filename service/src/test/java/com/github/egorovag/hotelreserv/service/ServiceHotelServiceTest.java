package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.ServiceHotelDao;
import com.github.egorovag.hotelreserv.model.ServiceHotel;
import com.github.egorovag.hotelreserv.service.impl.DefaultServiceHotelService;
import org.junit.jupiter.api.Assertions;
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

    private ServiceHotel service = new ServiceHotel(1, "pool", 50);

    @Test
    void readServiceByTypeOfService() {
        when(serviceHotelDao.readServiceByTypeOfServiceDao(service.getTypeOfService())).thenReturn(service);
        ServiceHotel serviceRes = defaultServiceHotelService.readServiceByTypeOfService(service.getTypeOfService());
        Assertions.assertEquals(service, serviceRes);
    }

    @Test
    void saveServiceListForOrder() {
        List<ServiceHotel> serviceList = new ArrayList<>();
        when(serviceHotelDao.saveServiceListForOrderDao(serviceList, 10)).thenReturn(true);
        boolean res = defaultServiceHotelService.saveServiceListForOrder(serviceList, 10);
        Assertions.assertTrue(res);
    }

    @Test
    void readServiceListByOrderId() {
        List<ServiceHotel> serviceList = new ArrayList<>();
        when(serviceHotelDao.readServiceListByOrderIdDao(10)).thenReturn(serviceList);
        List<ServiceHotel> serviceListRes = serviceHotelDao.readServiceListByOrderIdDao(10);
        Assertions.assertEquals(serviceList, serviceListRes);
    }
}