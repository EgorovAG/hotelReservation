package com.github.egorovag.hotelreserv.dao.converter;

import com.github.egorovag.hotelreserv.dao.entity.AuthUserEntity;
import com.github.egorovag.hotelreserv.model.AuthUser;

public class AuthUserConverter {
    public static AuthUser fromEntity(AuthUserEntity authUserEntity) {
        if (authUserEntity == null) {
            return null;
        }
        return new AuthUser(
                authUserEntity.getId(),
                authUserEntity.getLogin(),
                authUserEntity.getPassword(),
                authUserEntity.getRole(),
                ClientConverter.fromEntity(authUserEntity.getClientEntity()));
    }

    public static AuthUserEntity toEntity(AuthUser authUser) {
        if (authUser == null) {
            return null;
        }
        final AuthUserEntity authUserEntity = new AuthUserEntity();
        authUserEntity.setId(authUser.getId());
        authUserEntity.setLogin(authUser.getLogin());
        authUserEntity.setPassword(authUser.getPassword());
        authUserEntity.setRole(authUser.getRole());
        return authUserEntity;
    }
}
