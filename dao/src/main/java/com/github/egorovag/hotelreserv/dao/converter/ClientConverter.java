package com.github.egorovag.hotelreserv.dao.converter;

import com.github.egorovag.hotelreserv.dao.entity.ClientEntity;
import com.github.egorovag.hotelreserv.model.Client;

public class ClientConverter {
    public static Client fromEntity(ClientEntity clientEntity) {
        if (clientEntity == null) {
            return null;
        }
        return new Client(
                clientEntity.getId(),
                clientEntity.getFirstName(),
                clientEntity.getSecondName(),
                clientEntity.getEmail(),
                clientEntity.getPhone(),
                clientEntity.getUserId());
    }

    public static ClientEntity toEntity(Client client) {
        if (client == null) {
            return null;
        }
        final ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(client.getId());
        clientEntity.setFirstName(client.getFirstName());
        clientEntity.setSecondName(client.getSecondName());
        clientEntity.setEmail(client.getEmail());
        clientEntity.setPhone(client.getPhone());
        clientEntity.setUserId(client.getUserId());

        return clientEntity;
    }
}
