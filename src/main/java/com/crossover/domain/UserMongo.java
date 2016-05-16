package com.crossover.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by Ayman on 5/14/2016.
 */

@Document(collection = "User")
public class UserMongo {

    @Id
    private String id;

    @Field("Username")
    private String username;

    @Field("Password")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return String.format("UserMongo[id=%s, username=%s, password=%s]",
                                        id, username, password);
    }
}
