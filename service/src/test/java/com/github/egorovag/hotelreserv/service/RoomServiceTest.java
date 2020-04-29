//package com.github.egorovag.hotelreserv.service;
//
//import com.github.egorovag.hotelreserv.dao.IroomDao;
//import com.github.egorovag.hotelreserv.model.Room;
//import com.github.egorovag.hotelreserv.model.ClassRoom;
//import com.github.egorovag.hotelreserv.service.api.IroomService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class RoomServiceTest {
//
//    @Mock
//    private static IroomDao iroomDao;
//
//    @InjectMocks
//    private static IroomService iroomService;
//
//    @BeforeAll
//    static void createInstance(){
//        iroomService = RoomService.getInstance();
//    }
//
//    @Test
//    void testReadRoom_IdService() {
//        when(iroomDao.readRoom_IdDao(1,"ECONOM")).thenReturn(1);
//        Long result = iroomService.readRoom_IDService(1, "ECONOM");
//        Assertions.assertEquals(1, result);
//    }
//
//    @Test
//    void testReadRoomByIdService() {
//        Room room = new Room(1,2, ClassRoom.ECONOM,300);
//        when(iroomDao.readRoomByIdDao(1)).thenReturn(room);
//        Room result = iroomService.readRoomByIdService(1);
//        Assertions.assertEquals(room,result);
//    }
//}