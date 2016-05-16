package com.crossover.repository;

import com.crossover.domain.Demand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Ayman Elkfrawy on 5/14/2016.
 */

public interface DemandRepository extends JpaRepository<Demand, Long> {

    Demand findByUserIdAndBookId(long id, String bookid);
}
