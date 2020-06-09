package com.github.egorovag.hotelreserv.dao.converter;

import com.github.egorovag.hotelreserv.dao.entity.RoomEntity;
import com.github.egorovag.hotelreserv.dao.entity.ServiceHotelEntity;
import com.github.egorovag.hotelreserv.model.Room;
import com.github.egorovag.hotelreserv.model.ServiceHotel;

public class ServiceHotelConverter {
    public static ServiceHotel fromEntity(ServiceHotelEntity serviceHotelEntity) {
        if (serviceHotelEntity == null) {
            return null;
        }
        return new ServiceHotel(
                serviceHotelEntity.getServiceHotelId(),
                serviceHotelEntity.getTypeOfService(),
                serviceHotelEntity.getPrice());
    }

    public static ServiceHotelEntity toEntity(ServiceHotel serviceHotel) {
        if (serviceHotel == null) {
            return null;
        }
        final ServiceHotelEntity serviceHotelEntity = new ServiceHotelEntity();
        serviceHotelEntity.setServiceHotelId(serviceHotel.getServiceHotelId());
        serviceHotelEntity.setTypeOfService(serviceHotel.getTypeOfService());
        serviceHotelEntity.setPrice(serviceHotel.getPrice());

        return serviceHotelEntity;
    }
}
