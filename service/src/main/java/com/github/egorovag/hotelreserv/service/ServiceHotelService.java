package com.github.egorovag.hotelreserv.service;

import com.github.egorovag.hotelreserv.model.Service;

public interface ServiceHotelService {
    Service readServiceByTypeOfService(String typeOfService);
}
