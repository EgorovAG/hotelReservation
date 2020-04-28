package com.github.egorovag.hotelreserv.dao.api;

import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.OrderForClient;
import com.github.egorovag.hotelreserv.model.api.Condition;

import java.util.List;

public interface IOrderDao {

    OrderClient saveOrderDao (OrderClient orderWithoutId, int clientId);
    List<OrderForAdmin> readOrderListDao();
    List<OrderClient> readOrderByAuthUserIdDao(int id);
    boolean updateOrderListDao(int orderId, Condition condition);
    boolean deleteOrderByClientIdDao(int id);
    List<OrderForClient> readOrderForClientByClientIdDao(int id);
    int checkIdOrderByClientOrderDao( int orderId);
    boolean deleteOrderByOrderIdDao(int orderId);
    Condition readConditionByOrderIdDao(int orderId);
    int readPriceByOrderIdDao(int orderId);





}
