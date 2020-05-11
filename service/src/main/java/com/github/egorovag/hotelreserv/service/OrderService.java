package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.Service;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.util.List;

import static com.github.egorovag.hotelreserv.model.enums.Condition.CONSIDERATION;

public interface OrderService {

    //    OrderClient saveOrder(Client client, int numOfSeats, ClassRoom classOfAp,String startDate,String endDate,
//                          Condition condition, List<Service> typeOfService);
    boolean saveOrder(OrderClient order);

    List<OrderForAdmin> readOrderListService();

    //    List<OrderClient> readOrderByAuthUserIdService(int id);
    boolean updateOrderList(int orderId, Condition condition);

    boolean deleteOrderByClientId(int id);

    int readPriceForRoomByOrderId(int orderId);

    List<OrderForClient> readOrderForClientByClient_Id(int id);

    boolean checkIdOrderByClientOrder(int orderId, int clientId);

    boolean deleteOrderByOrderId(int orderId);

    Condition readConditionByOrderId(int orderId);


}
