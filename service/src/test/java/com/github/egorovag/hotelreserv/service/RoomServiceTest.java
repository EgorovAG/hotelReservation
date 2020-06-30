package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.dao.RoomDao;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.enums.ClassRoom;
import com.github.egorovag.hotelreserv.service.impl.DefaultRoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    RoomDao roomDao;

    @InjectMocks
    DefaultRoomService defaultRoomService;

    private Room room = new Room(1, 1, ClassRoom.ECONOM);

    @Test
    void testReadRoomByNumOfSeatsAndClassOfApDao() {
        when(roomDao.readRoomByNumOfSeatsAndClassOfApDao(room.getId(), room.getClassOfAp())).thenReturn(room);
        Room roomRes = defaultRoomService.readRoomByNumOfSeatsAndClassOfAp(room.getId(), room.getClassOfAp());
        Assertions.assertEquals(room, roomRes);
    }
}