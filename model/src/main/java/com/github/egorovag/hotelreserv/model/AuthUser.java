package com.github.egorovag.hotelreserv.model;

import com.github.egorovag.hotelreserv.model.enums.Role;

public class AuthUser {

    private Integer id;
    private String login;
    private String password;
    private Role role;

    private Client client;

    public AuthUser(Integer id, String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public AuthUser(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public AuthUser(Integer id, String login, String password, Role role, Client client) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
