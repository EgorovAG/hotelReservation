package com.github.egorovag.hotelreserv.dao.repository;

import com.github.egorovag.hotelreserv.dao.entity.BlackListEntity;
import com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlackListJpaRepository extends JpaRepository<BlackListEntity, Integer> {

    @Query("select new com.github.egorovag.hotelreserv.model.dto.BlackListUsersDTO (bl.id, bl.userId, c.firstName, " +
            "c.secondName, bl.dateBlock ) from BlackListEntity bl join ClientEntity c on bl.userId = c.userId")
    List<BlackListUsersDTO> readBlackListUsersListsDaoSD();

    BlackListEntity findBlackListEntityByUserId(int id);


}