package com.github.egorovag.hotelreserv.model.dto;

import java.util.Objects;

public class AuthUserWithClient {

    private Integer clientId;
    private Integer userId;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
    private String phone;

    public AuthUserWithClient(Integer clientId, Integer userId, String login, String password, String firstName,
                              String secondName, String email, String phone) {
        this.clientId = clientId;
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
    }

    public AuthUserWithClient() {
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return   "clientId=" + clientId +
                ", Логин=" + login +
                ", Пароль=" + password +
                ", Имя=" + firstName +
                ", Фамилия=" + secondName +
                ", email=" + email +
                ", phone=" + phone ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUserWithClient that = (AuthUserWithClient) o;
        return Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }
}
