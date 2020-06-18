package com.github.egorovag.hotelreserv.model;


import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Client {

    private Integer id;
    @Pattern(regexp = "[A-Za-zА-Яа-я]{1,50}", message = "Имя должно содержать не больше 50 символов и состоять только из букв")
    private String firstName;
    @Pattern(regexp = "[A-Za-zА-Яа-я]{1,50}", message = "Фамилия должно содержать не больше 50 символов и состоять только из букв")
    private String secondName;
    @Pattern(regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$", message = "Формат email должен быть такой nick@mail.com")
    private String email;
    @Pattern(regexp = "^\\+\\d{12}", message = "Формат номера телефона должен быть такой: +375291111111")
    private String phone;
    private Integer userId;

    public Client() {
    }

    public Client(Integer id, String firstName, String secondName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
    }

    public Client(Integer id, String firstName, String secondName, String email, String phone, Integer userId) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
