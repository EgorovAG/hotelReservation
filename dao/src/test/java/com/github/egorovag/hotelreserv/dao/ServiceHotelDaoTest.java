package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.config.DaoConfig;
import com.github.egorovag.hotelreserv.model.*;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
class ServiceHotelDaoTest {

    @Autowired
    AuthUserDao authUserDao;
    @Autowired
    ClientDao clientDao;
    @Autowired
    OrderDao orderDao;
    private AuthUser authUser;
    private OrderClient orderClient;
    private Client client;
    private Room room;
    private List<ServiceHotel> serviceList;
    @Autowired
    ServiceHotelDao serviceHotelDao;

    @BeforeEach
    void saveAuthUserAndClientAndOrderClientWithService() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex", "Alexandrov", "alex@tut.by", "55555");
        authUser = authUserDao.saveAuthUserAndClientDao(authUser, client);
        room = new Room(1, 1, ClassRoom.ECONOM);
        client = new Client(authUser.getClient().getId(), "Alex", "Alexandrov", "alex@tut.by", "55555");
        orderClient = new OrderClient(null, LocalDate.of(2020, 10, 05), LocalDate.of(2020, 10, 07), room.getId(), client.getId(),
                Condition.CONSIDERATION);
        orderClient = orderDao.saveOrderDao(orderClient, room, client);
        serviceList = new ArrayList<>();
        ServiceHotel service1 = serviceHotelDao.readServiceByTypeOfServiceDao("pool");
        ServiceHotel service2 = serviceHotelDao.readServiceByTypeOfServiceDao("wifi");
        serviceList.add(service1);
        serviceList.add(service2);
    }

    @Test
    void testReadServiceByTypeOfService() {
        String typeOfService = "pool";
        ServiceHotel service = serviceHotelDao.readServiceByTypeOfServiceDao(typeOfService);
        Assertions.assertEquals(1, service.getServiceHotelId());
        Assertions.assertEquals(50, service.getPrice());
    }

    @Test
    void testSaveServiceListForOrder() {
        boolean res = serviceHotelDao.saveServiceListForOrderDao(serviceList, orderClient.getOrderId());
        Assertions.assertTrue(res);
    }

    @Test
    void testReadServiceListByOrderIdDao() {
        serviceHotelDao.saveServiceListForOrderDao(serviceList, orderClient.getOrderId());
        List<ServiceHotel> serviceListRes = serviceHotelDao.readServiceListByOrderIdDao(orderClient.getOrderId());
        Assertions.assertEquals(2, serviceListRes.size());
    }
}