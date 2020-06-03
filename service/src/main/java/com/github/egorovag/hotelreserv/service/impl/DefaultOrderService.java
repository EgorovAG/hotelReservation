package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.OrderDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultOrderDao;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.Service;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import com.github.egorovag.hotelreserv.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class DefaultOrderService implements OrderService {

    private final OrderDao orderDao;

    public DefaultOrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    @Transactional
    public OrderClient saveOrder(OrderClient orderClient) {
        return orderDao.saveOrderDao(orderClient);
    }

    @Override
    @Transactional
    public List<OrderForAdmin> readOrderList() {
        return orderDao.readOrderListDao();
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
    public List<OrderForClient> readOrderForClientByClientId(int id) {
        return orderDao.readOrderForClientByClientIdDao(id);
    }

    @Override
    @Transactional
    public List<OrderClient> readOrderClientListByClientId(int clientId) {
        return orderDao.readOrderClientListByClientIdDao(clientId);
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
