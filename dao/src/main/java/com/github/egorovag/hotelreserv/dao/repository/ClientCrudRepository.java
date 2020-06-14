package com.github.egorovag.hotelreserv.dao.repository;

import com.github.egorovag.hotelreserv.dao.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClientCrudRepository extends CrudRepository<ClientEntity,Integer> {
}
