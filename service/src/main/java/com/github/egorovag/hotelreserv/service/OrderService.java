package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.util.List;

public interface OrderService {

    OrderClient saveOrder(OrderClient orderWithoutId, int client_id);
    List<OrderForAdmin> readOrderListService();
//    List<OrderClient> readOrderByAuthUserIdService(int id);
    boolean updateOrderList(int orderId, Condition condition);
    boolean deleteOrderByClientId(int id);
    int readPriceByOrderId(int orderId);
    List<OrderForClient> readOrderForClientByClient_Id(int id);
    boolean checkIdOrderByClientOrder(int orderId,int clientId);
    boolean deleteOrderByOrderId(int orderId);
    Condition readConditionByOrderId(int orderId);


}
