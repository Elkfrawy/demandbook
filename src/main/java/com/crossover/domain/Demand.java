package com.crossover.domain;

import javax.persistence.*;

/**
 * Created by Ayman on 5/14/2016.
 */

@Entity
public class Demand {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Column(name = "book_id", nullable = false)
    private String bookId;

    @Column(name = "book_title")
    private String bookTitle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Override
    public String toString() {
        return String.format("Demand[%s, %s, %s, %s", id, getUser().getId(), bookId, bookTitle);
    }
}
