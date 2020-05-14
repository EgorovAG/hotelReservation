package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.dao.impl.DefaultAuthUserDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultClientDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultOrderDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultServiceHotelDao;
import com.github.egorovag.hotelreserv.model.*;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.model.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ServiceHotelDaoTest {
    private ClientDao clientDao = DefaultClientDao.getInstance();
    private AuthUserDao authUserDao = DefaultAuthUserDao.getInstance();
    private OrderDao orderDao = DefaultOrderDao.getInstance();
    private AuthUser authUser;
    private OrderClient orderClient;
    private Client client;
    private Room room;
    private List<Service> serviceList;
    private ServiceHotelDao serviceHotelDao = DefaultServiceHotelDao.getInstance();

    @BeforeEach
    void saveAuthUserAndClientAndOrderClientWithService() {
        authUser = new AuthUser("alex", "pass", Role.USER);
        client = new Client(null, "Alex","Alexandrov","alex@tut.by","55555",authUser);
        authUser = clientDao.saveAuthUserAndClientDao(authUser,client);
        room = new Room(1,1,ClassRoom.ECONOM);
        client = new Client(authUser.getClient().getId(), "Alex","Alexandrov","alex@tut.by","55555",authUser );
        orderClient = new OrderClient(null, "2020-10-05", "2020-10-10", room.getId(), client.getId(),
                Condition.CONSIDERATION, room, client);
        orderClient = orderDao.saveOrderDao(orderClient);
        serviceList = new ArrayList<>();
        Service service1 = serviceHotelDao.readServiceByTypeOfServiceDao("pool");
        Service service2 = serviceHotelDao.readServiceByTypeOfServiceDao("wifi");
        serviceList.add(service1);
        serviceList.add(service2);
    }

    @AfterEach
    void deleteAuthUserAndClientAndOrderClient() {
        clientDao.deleteAuthUserAndClientByUserIdDao(authUser.getId());
    }

    @Test
    void testReadServiceByTypeOfService() {
        String typeOfService = "pool";
        Service service = serviceHotelDao.readServiceByTypeOfServiceDao(typeOfService);
        Assertions.assertEquals(1, service.getServiceId());
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
        List<Service> serviceListRes = serviceHotelDao.readServiceListByOrderIdDao(orderClient.getOrderId());
        Assertions.assertEquals(2, serviceListRes.size());
    }
}