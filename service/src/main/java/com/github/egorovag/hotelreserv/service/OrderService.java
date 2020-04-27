package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.OrderDao;
import com.github.egorovag.hotelreserv.dao.api.IOrderDao;
import com.github.egorovag.hotelreserv.dao.api.IclientDao;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.OrderForClient;
import com.github.egorovag.hotelreserv.model.api.Condition;
import com.github.egorovag.hotelreserv.service.api.IcheckUserService;
import com.github.egorovag.hotelreserv.service.api.IorderService;

import java.util.List;

public class OrderService implements IorderService {

    private IOrderDao iOrderDao = OrderDao.getInstance();
    private static volatile IorderService instance;

    public static IorderService getInstance() {
        IorderService localInstance = instance;
        if (localInstance == null) {
            synchronized (IcheckUserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new OrderService();
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
    public List<OrderClient> readOrderByAuthUserIdService(int id) {
        return iOrderDao.readOrderByAuthUserIdDao(id);
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
