package com.crossover.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by Ayman on 5/14/2016.
 */

@Document(collection = "Book")
public class Book {

    @Id
    private String id;

    @Field("Title")
    private String title;

    @Field("Description")
    private String description;

    @Field("Authors")
    private String[] authors;

    @Field("Publisher")
    private String publisher;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return String.format("Book[id=%s, title=%s, Authors=%s, Description=%s]",
                id, title, authors.toString(),
                (description!=null && description.length() > 50)? description.substring(0,50)+"...": description);
    }
}
