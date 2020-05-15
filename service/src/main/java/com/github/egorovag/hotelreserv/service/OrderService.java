package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.Service;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.util.ArrayList;
import java.util.List;

import static com.github.egorovag.hotelreserv.model.enums.Condition.CONSIDERATION;

public interface OrderService {

    OrderClient saveOrder(OrderClient order);

    List<OrderForAdmin> readOrderList();

    boolean updateOrderList(int orderId, Condition condition);


    int readPriceForRoomByOrderId(int orderId);

    List<OrderForClient> readOrderForClientByClientId(int clientId);

    List<OrderClient> readOrderClientListByClientId(int clientId);

    boolean checkIdOrderByClientOrder(int orderId, int clientId);

    boolean deleteOrderByOrderId(int orderId);

    Condition readConditionByOrderId(int orderId);



//    boolean deleteOrderByClientId(int id);
//    List<OrderClient> readOrderByAuthUserIdService(int id);


}
