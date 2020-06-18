package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.OrderDao;
import com.github.egorovag.hotelreserv.model.Client;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdminDTO;
import com.github.egorovag.hotelreserv.model.dto.OrderForClientDTO;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultOrderService implements OrderService {

    private final OrderDao orderDao;

    public DefaultOrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public OrderClient saveOrder(OrderClient orderClient, Room room, Client client) {
        return orderDao.saveOrderDao(orderClient, room, client);
    }

    @Override
    @Transactional
    public List<OrderForAdminDTO> readOrderListForAdminDTO() {
        return orderDao.readOrderListForAdminDTODao();
    }

    @Override
    @Transactional
    public boolean updateOrderList(int order_id, Condition condition) {
        if (orderDao.updateOrderListDao(order_id, condition)) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public int readPriceForRoomByOrderId(int orderId) {
        return orderDao.readPriceForRoomByOrderIdDao(orderId);
    }

    @Override
    @Transactional
    public List<OrderForClientDTO> readOrderForClientDTOByClientId(int id) {
        return orderDao.readOrderForClientDTOByClientIdDao(id);
    }

    @Override
    @Transactional
    public List<OrderClient> readOrderClientListByClientId(int clientId) {
        return orderDao.readOrderClientListByClientIdDao(clientId);
    }

    @Override
    @Transactional
    public List<OrderClient> readOrderClientList() {
        return orderDao.readOrderClientListDao();
    }

    @Override
    @Transactional
    public boolean checkIdOrderByClientOrder(int orderId, int clientId) {
        if (orderDao.checkIdOrderByClientOrderDao(orderId) == clientId) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean deleteOrderByOrderId(int orderId) {
        return orderDao.deleteOrderByOrderIdDao(orderId);
    }

    @Override
    @Transactional
    public Condition readConditionByOrderId(int orderId) {
        return orderDao.readConditionByOrderIdDao(orderId);
    }
}


//    @Override
//    public boolean deleteOrderByClientId(int id) {
//        if (orderDao.deleteOrderByClientIdDao(id)) {
//            return true;
//        }
//        return false;
//    }


//    @Override
//    public List<OrderClient> readOrderByAuthUserIdService(int id) {
//        return iOrderDao.readOrderByAuthUserIdDao(id);
//    }
