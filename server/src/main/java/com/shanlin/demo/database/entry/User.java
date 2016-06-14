package com.shanlin.demo.database.entry;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Shanlin on 16/6/4.
 */
@Entity
@Table(name = "t_user")
public class User extends AbstractPersistable<Long> {

    @Column(unique = true, name = "username")
    private String username;

    @Column(nullable = false, name = "password")
    private String password;

    private String token;

    private Timestamp createTokenTime;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getCreateTokenTime() {
        return createTokenTime;
    }

    public void setCreateTokenTime(Timestamp createTokenTime) {
        this.createTokenTime = createTokenTime;
    }
}
