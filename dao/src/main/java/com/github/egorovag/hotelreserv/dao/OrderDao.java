package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdminDTO;
import com.github.egorovag.hotelreserv.model.dto.OrderForClientDTO;
import com.github.egorovag.hotelreserv.model.enums.Condition;

import java.util.List;

public interface OrderDao {

    OrderClient saveOrderDao(OrderClient orderClient, Room room, Client client);

    List<OrderForAdminDTO> readOrderListForAdminDTODao();

    boolean updateOrderListDao(int orderId, Condition condition);

    List<OrderForClientDTO> readOrderForClientDTOByClientIdDao(int id);

    List<OrderClient> readOrderClientListByClientIdDao(int clientId);

    List<OrderClient> readOrderClientListDao();

    int checkIdOrderByClientOrderDao(int orderId);

    boolean deleteOrderByOrderIdDao(int orderId);

    Condition readConditionByOrderIdDao(int orderId);

    int readPriceForRoomByOrderIdDao(int orderId);
}
