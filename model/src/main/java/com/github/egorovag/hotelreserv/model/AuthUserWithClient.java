package com.github.egorovag.hotelreserv.model;

import com.github.egorovag.hotelreserv.model.api.Role;

import javax.persistence.*;

public class AuthUserWithClient {

    private int id;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private String email;
    private String phone;

    public AuthUserWithClient(int id, String login, String password, String firstName, String secondName, String email, String phone) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return   "id=" + id +
                ", Логин=" + login +
                ", Пароль=" + password +
                ", Имя=" + firstName +
                ", Фамилия=" + secondName +
                ", email=" + email +
                ", phone=" + phone ;
    }
}
