package com.github.egorovag.hotelreserv.dao.converter;

import com.github.egorovag.hotelreserv.dao.entity.BlackListEntity;
import com.github.egorovag.hotelreserv.model.BlackList;

public class BlackListConverter {
    public static BlackList fromEntity(BlackListEntity blackListEntity) {
        if (blackListEntity == null) {
            return null;
        }
        return new BlackList(
                blackListEntity.getId(),
                blackListEntity.getUserId(),
                blackListEntity.getDateBlock());
    }

    public static BlackListEntity toEntity(BlackList blackList) {
        if (blackList == null) {
            return null;
        }
        final BlackListEntity blackListEntity = new BlackListEntity();
        blackListEntity.setId(blackList.getId());
        blackListEntity.setUserId(blackList.getUserId());
        blackListEntity.setDateBlock(blackList.getDateBlock());
        return blackListEntity;
    }
}
