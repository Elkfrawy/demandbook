package com.crossover.repository;

import com.crossover.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Ayman on 5/14/2016.
 */
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByTitleLike(String title);

    List<Book> findByDescriptionLikeIgnoreCase(String description);

    List<Book> findByDescriptionLikeOrTitleLikeIgnoreCase(String descQuery, String titleQuery);

    List<Book> findByAuthorsLikeIgnoreCase(String author);

    List<Book> findByPublisherLike(String publisher);

}