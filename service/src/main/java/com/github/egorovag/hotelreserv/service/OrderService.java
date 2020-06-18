package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdminDTO;
import com.github.egorovag.hotelreserv.model.dto.OrderForClientDTO;
import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.util.List;

public interface OrderService {

    OrderClient saveOrder(OrderClient order, Room room, Client client);

    List<OrderForAdminDTO> readOrderListForAdminDTO();

    boolean updateOrderList(int orderId, Condition condition);

    int readPriceForRoomByOrderId(int orderId);

    List<OrderForClientDTO> readOrderForClientDTOByClientId(int clientId);

    List<OrderClient> readOrderClientListByClientId(int clientId);

    List<OrderClient> readOrderClientList();

    boolean checkIdOrderByClientOrder(int orderId, int clientId);

    boolean deleteOrderByOrderId(int orderId);

    Condition readConditionByOrderId(int orderId);


//    boolean deleteOrderByClientId(int id);
//    List<OrderClient> readOrderByAuthUserIdService(int id);


}
