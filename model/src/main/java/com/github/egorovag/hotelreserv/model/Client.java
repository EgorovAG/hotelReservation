package com.github.egorovag.hotelreserv.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "client")
public class Client {

    private Integer id;
    private String firstName;
    private String secondName;
    private String email;
    private String phone;
    private Integer userId;

    private AuthUser authUser;
    private List<OrderClient> orderClients = new ArrayList<>();

    public Client(Integer id, String firstName, String secondName, String email, String phone, Integer userId,
                  AuthUser authUser, List<OrderClient> orderClients) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
        this.authUser = authUser;
        this.orderClients = orderClients;
    }

    public Client(Integer id, String firstName, String secondName, String email, String phone, Integer userId,
                  List<OrderClient> orderClients) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
        this.orderClients = orderClients;
    }

    public Client(Integer id, String firstName, String secondName, String email, String phone, AuthUser authUser) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.authUser = authUser;
    }

    public Client(String firstName, String secondName, String email, String phone, Integer userId) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
    }

    public Client(Integer id, String firstName, String secondName, String email, String phone, Integer userId) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
        this.userId = userId;
    }

    public Client(String firstName, String secondName, String email, String phone) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.phone = phone;
    }

    public Client() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "user_id", insertable = false, updatable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public AuthUser getAuthUser() {
        return authUser;
    }

    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<OrderClient> getOrderClients() {
        return orderClients;
    }

    public void setOrderClients(List<OrderClient> orderClients) {
        this.orderClients = orderClients;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
