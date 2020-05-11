package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.OrderDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultOrderDao;
import com.github.egorovag.hotelreserv.model.OrderClient;
import com.github.egorovag.hotelreserv.model.dto.OrderForAdmin;
import com.github.egorovag.hotelreserv.model.dto.OrderForClient;
import com.github.egorovag.hotelreserv.model.enums.Condition;
import com.github.egorovag.hotelreserv.service.AuthUserService;
import com.github.egorovag.hotelreserv.service.OrderService;

import java.util.List;

public class DefaultOrderService implements OrderService {

    private OrderDao orderDao = DefaultOrderDao.getInstance();
    private static volatile OrderService instance;

    public static OrderService getInstance() {
        OrderService localInstance = instance;
        if (localInstance == null) {
            synchronized (AuthUserService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultOrderService();
                }
            }
        }
        return localInstance;
    }

    @Override
//    public OrderClient saveOrder(Client client, int numOfSeats, ClassRoom classOfAp, String startDate, String endDate, Condition condition, List<Service> serviceList) {
//        return iOrderDao.saveOrderDao(client, numOfSeats, classOfAp, startDate, endDate, condition, serviceList);
    public boolean saveOrder(OrderClient orderClient) {
        return orderDao.saveOrderDao(orderClient);
    }

    @Override
    public List<OrderForAdmin> readOrderListService() {
        return orderDao.readOrderListDao();
    }



    @Override
    public boolean updateOrderList(int order_id, Condition condition) {
        if (orderDao.updateOrderListDao(order_id, condition)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteOrderByClientId(int id) {
        if (orderDao.deleteOrderByClientIdDao(id)) {
            return true;
        }
        return false;
    }

    @Override
    public int readPriceForRoomByOrderId(int orderId) {
        return orderDao.readPriceForRoomByOrderIdDao(orderId);
    }

    @Override
    public List<OrderForClient> readOrderForClientByClient_Id(int id) {
        return orderDao.readOrderForClientByClientIdDao(id);
    }

    @Override
    public boolean checkIdOrderByClientOrder(int orderId, int clientId) {
        if (orderDao.checkIdOrderByClientOrderDao(orderId) == clientId){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteOrderByOrderId(int orderId) {
        return orderDao.deleteOrderByOrderIdDao(orderId);

    }

    @Override
    public Condition readConditionByOrderId(int orderId) {
       return orderDao.readConditionByOrderIdDao(orderId);
    }
}

// вроде лишнее
//    @Override
//    public List<OrderClient> readOrderByAuthUserIdService(int id) {
//        return iOrderDao.readOrderByAuthUserIdDao(id);
//    }
