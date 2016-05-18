package com.crossover.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Ayman on 5/14/2016.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Demand> demands;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Demand> getDemands() {
        return demands;
    }

    public void setDemands(List<Demand> demands) {
        this.demands = demands;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return String.format("User[id=%s, username=%s, password=%s, email=%s]",
                                    id, username, password, email);
    }

    public boolean hasDemandForBook(Book book) {
        for (Demand demand : getDemands()) {
            if (demand.getBookId() != null &&
                    demand.getBookId().equals(book.getId()))
                return true;
        }
        return false;
    }
}
