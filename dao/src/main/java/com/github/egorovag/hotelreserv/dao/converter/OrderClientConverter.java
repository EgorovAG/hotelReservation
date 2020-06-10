package com.github.egorovag.hotelreserv.dao.converter;

import com.github.egorovag.hotelreserv.dao.entity.OrderClientEntity;
import com.github.egorovag.hotelreserv.model.OrderClient;

public class OrderClientConverter {
    public static OrderClient fromEntity(OrderClientEntity orderClientEntity) {
        if (orderClientEntity == null) {
            return null;
        }
        return new OrderClient(
                orderClientEntity.getOrderId(),
                orderClientEntity.getStartDate(),
                orderClientEntity.getEndDate(),
                orderClientEntity.getRoomId(),
                orderClientEntity.getClientId(),
                orderClientEntity.getCondition());
    }

    public static OrderClientEntity toEntity(OrderClient orderClient) {
        if (orderClient == null) {
            return null;
        }
        final OrderClientEntity orderClientEntity = new OrderClientEntity();
        orderClientEntity.setOrderId(orderClient.getOrderId());
        orderClientEntity.setStartDate(orderClient.getStartDate());
        orderClientEntity.setEndDate(orderClient.getEndDate());
        orderClientEntity.setRoomId(orderClient.getRoomId());
        orderClientEntity.setClientId(orderClient.getClientId());
        orderClientEntity.setCondition(orderClient.getCondition());

        return orderClientEntity;
    }
}
