package com.github.egorovag.hotelreserv.dao.repository;

import com.github.egorovag.hotelreserv.dao.entity.BlackListEntity;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlackListJpaRepository extends JpaRepository<BlackListEntity, Integer> {

    @Query("select new com.github.egorovag.hotelreserv.model.dto.BlackListUsers (bl.id, bl.userId, c.firstName, " +
            "c.secondName, bl.dateBlock ) from BlackListEntity bl join ClientEntity c on bl.userId = c.userId")
    List<BlackListUsers> readBlackListUsersListsDaoSD();

    BlackListEntity findBlackListEntityByUserId(int id);


}