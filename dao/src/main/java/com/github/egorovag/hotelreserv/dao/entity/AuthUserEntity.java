package com.github.egorovag.hotelreserv.dao.entity;

import com.github.egorovag.hotelreserv.model.enums.Role;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "authUser")
public class AuthUserEntity {

    private Integer id;
    private String login;
    private String password;
    private Role role;

    private ClientEntity clientEntity;
    private BlackListEntity blackListEntity;


    public AuthUserEntity(Integer id, String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public AuthUserEntity(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

//    public AuthUserEntity(Integer id, String login, String password, Role role, ClientEntity clientEntity, BlackListEntity blackListEntity) {
//        this.id = id;
//        this.login = login;
//        this.password = password;
//        this.role = role;
//        this.clientEntity = clientEntity;
//        this.blackListEntity = blackListEntity;
//    }



    public AuthUserEntity() {
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
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToOne(mappedBy = "authUserEntity", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    @OneToOne(mappedBy = "authUserEntity", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    public BlackListEntity getBlackListEntity() {
        return blackListEntity;
    }

    public void setBlackListEntity(BlackListEntity blackListEntity) {
        this.blackListEntity = blackListEntity;
    }

    @Override
    public String toString() {
        return "AuthUser{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthUserEntity authUser = (AuthUserEntity) o;
        return Objects.equals(id, authUser.id) &&
                Objects.equals(login, authUser.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }
}
