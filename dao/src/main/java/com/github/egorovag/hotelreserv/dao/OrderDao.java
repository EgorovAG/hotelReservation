package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.Service;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.util.List;

public interface OrderDao {

    //    OrderClient saveOrderDao (Client client, int numOfSeats, ClassRoom classOfAp, String startDate, String endDate,
//                              Condition condition, List<Service> serviceList);
    boolean saveOrderDao(OrderClient orderClient);

    List<OrderForAdmin> readOrderListDao();

    boolean updateOrderListDao(int orderId, Condition condition);

    boolean deleteOrderByClientIdDao(int id);

    List<OrderForClient> readOrderForClientByClientIdDao(int id);

    int checkIdOrderByClientOrderDao(int orderId);

    boolean deleteOrderByOrderIdDao(int orderId);

    Condition readConditionByOrderIdDao(int orderId);

    int readPriceForRoomByOrderIdDao(int orderId);

//    List<OrderClient> readOrderByAuthUserIdDao(int id);


}
