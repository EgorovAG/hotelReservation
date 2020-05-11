package com.github.egorovag.hotelreserv.service.impl;

import com.github.egorovag.hotelreserv.dao.ServiceHotelDao;
import com.github.egorovag.hotelreserv.dao.impl.DefaultServiceHotelDao;
import com.github.egorovag.hotelreserv.model.Service;
import com.github.egorovag.hotelreserv.service.ServiceHotelService;

public class DefaultServiceHotelService implements ServiceHotelService {

    private ServiceHotelDao serviceHotelDao = DefaultServiceHotelDao.getInstance();
    private static volatile ServiceHotelService instance;

    public static ServiceHotelService getInstance() {
        ServiceHotelService localInstance = instance;
        if (localInstance == null) {
            synchronized (ServiceHotelService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DefaultServiceHotelService();
                }
            }
        }
        return localInstance;
    }

    @Override
    public Service readServiceByTypeOfService(String typeOfService) {
        return serviceHotelDao.readServiceByTypeOfService(typeOfService);
    }
}
