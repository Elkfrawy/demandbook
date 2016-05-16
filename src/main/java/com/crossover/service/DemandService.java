package com.crossover.service;

import com.crossover.domain.Demand;
import com.crossover.domain.User;

import java.util.List;

/**
 * Created by Ayman on 5/14/2016.
 */
public interface DemandService {

    List<Demand> getAllDemands();

    boolean addDemand(Demand demandToAdd);
    boolean deleteDemand(Demand demand);
    boolean updateDemand(Demand demand);
    Demand getDemandByUseridAndBookid(long id, String bookid);
}
