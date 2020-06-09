package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.util.List;

public interface OrderDao {

//    OrderClient saveOrderDao(OrderClient orderClient);

    OrderClient saveOrderDao(OrderClient orderClient, Room room, Client client);

    List<OrderForAdmin> readOrderListForAdminDao();

    boolean updateOrderListDao(int orderId, Condition condition);

    List<OrderForClient> readOrderForClientByClientIdDao(int id);

    List<OrderClient> readOrderClientListByClientIdDao(int clientId);

    List<OrderClient> readOrderClientListDao();

    int checkIdOrderByClientOrderDao(int orderId);

    boolean deleteOrderByOrderIdDao(int orderId);

    Condition readConditionByOrderIdDao(int orderId);

    int readPriceForRoomByOrderIdDao(int orderId);



//    List<OrderClient> readOrderByAuthUserIdDao(int id);
//    boolean deleteOrderByClientIdDao(int id);


}
