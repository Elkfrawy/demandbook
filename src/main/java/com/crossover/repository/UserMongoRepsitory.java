package com.crossover.repository;

import com.crossover.domain.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Ayman on 5/14/2016.
 */
public interface UserMongoRepsitory extends MongoRepository<UserMongo, String> {

}