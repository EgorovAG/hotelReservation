package com.github.egorovag.hotelreserv.dao.converter;

import com.github.egorovag.hotelreserv.dao.entity.RoomEntity;
import com.github.egorovag.hotelreserv.model.Room;

public class RoomConverter {
    public static Room fromEntity(RoomEntity roomEntity) {
        if (roomEntity == null) {
            return null;
        }
        return new Room(
                roomEntity.getId(),
                roomEntity.getNumOfSeats(),
                roomEntity.getClassOfAp(),
                roomEntity.getPrice());
    }

    public static RoomEntity toEntity(Room room) {
        if (room == null) {
            return null;
        }
        final RoomEntity roomEntity = new RoomEntity();
        roomEntity.setId(room.getId());
        roomEntity.setNumOfSeats(room.getNumOfSeats());
        roomEntity.setClassOfAp(room.getClassOfAp());
        roomEntity.setPrice(room.getPrice());

        return roomEntity;
    }
}
