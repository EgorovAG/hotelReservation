package com.github.egorovag.hotelreserv.dao;

import com.github.egorovag.hotelreserv.model.Service;

import java.util.List;

public interface ServiceDao {
    List<Service> readService();

}
