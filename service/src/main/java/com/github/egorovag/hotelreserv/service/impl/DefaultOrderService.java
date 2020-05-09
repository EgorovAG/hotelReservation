package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.OrderDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultOrderDao;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.UserService;
import com.github.egorovag.hotelreserv.service.OrderService;

import java.util.List;

public class DefaultOrderService implements OrderService {

    private OrderDao iOrderDao = DefaultOrderDao.getInstance();
    private static volatile OrderService instance;

    public static OrderService getInstance() {
        OrderService localInstance = instance;
        if (localInstance == null) {
            synchronized (UserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultOrderService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public OrderClient saveOrder(OrderClient orderWithoutId, int client_id) {
        return iOrderDao.saveOrderDao(orderWithoutId, client_id);
    }

    @Override
    public List<OrderForAdmin> readOrderListService() {
        return iOrderDao.readOrderListDao();
    }



    @Override
    public boolean updateOrderList(int order_id, Condition condition) {
        if (iOrderDao.updateOrderListDao(order_id, condition)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteOrderByClientId(int id) {
        if (iOrderDao.deleteOrderByClientIdDao(id)) {
            return true;
        }
        return false;
    }

    @Override
    public int readPriceByOrderId(int orderId) {
        return iOrderDao.readPriceByOrderIdDao(orderId);
    }

    @Override
    public List<OrderForClient> readOrderForClientByClient_Id(int id) {
        return iOrderDao.readOrderForClientByClientIdDao(id);
    }

    @Override
    public boolean checkIdOrderByClientOrder(int orderId, int clientId) {
        if (iOrderDao.checkIdOrderByClientOrderDao(orderId) == clientId){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteOrderByOrderId(int orderId) {
        return iOrderDao.deleteOrderByOrderIdDao(orderId);

    }

    @Override
    public Condition readConditionByOrderId(int orderId) {
       return iOrderDao.readConditionByOrderIdDao(orderId);
    }
}

// вроде лишнее
//    @Override
//    public List<OrderClient> readOrderByAuthUserIdService(int id) {
//        return iOrderDao.readOrderByAuthUserIdDao(id);
//    }
