package com.github.egorovag.hotelreserv.dao.repository;

import com.github.egorovag.hotelreserv.dao.entity.AuthUserEntity;
import com.github.egorovag.hotelreserv.model.dto.AuthUserWithClientDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthUserJpaRepository extends JpaRepository<AuthUserEntity, Integer> {

    AuthUserEntity findByLogin(String login);

    AuthUserEntity findAuthUserEntityByLogin(String login);

    @Query("select new com.github.egorovag.hotelreserv.model.dto.AuthUserWithClientDTO (c.id, a.id, a.login,a.password," +
            "c.firstName,c.secondName,c.email,c.phone) from AuthUserEntity a join ClientEntity c on a.id = c.userId")
    List<AuthUserWithClientDTO> readListAuthUserWithClientDaoSD();


    @Query("select new com.github.egorovag.hotelreserv.model.dto.AuthUserWithClientDTO (c.id, a.id, a.login,a.password," +
            "c.firstName,c.secondName,c.email,c.phone) from AuthUserEntity a join ClientEntity c on a.id = c.userId")
    List<AuthUserWithClientDTO> readListAuthUserWithClientPaginationDaoSD(Pageable pageable);


}
