package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.util.List;

public interface OrderDao {

    OrderClient saveOrderDao (OrderClient orderWithoutId, int clientId);
    List<OrderForAdmin> readOrderListDao();
    boolean updateOrderListDao(int orderId, Condition condition);
    boolean deleteOrderByClientIdDao(int id);
    List<OrderForClient> readOrderForClientByClientIdDao(int id);
    int checkIdOrderByClientOrderDao( int orderId);
    boolean deleteOrderByOrderIdDao(int orderId);
    Condition readConditionByOrderIdDao(int orderId);
    int readPriceByOrderIdDao(int orderId);

//    List<OrderClient> readOrderByAuthUserIdDao(int id);






}
