package com.crossover.service.imp;

import com.crossover.domain.Demand;
import com.crossover.repository.DemandRepository;
import com.crossover.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ayman Elkfrawy on 5/16/2016.
 */

@Service
public class DemandServiceImpl implements DemandService {

    @Autowired
    DemandRepository demandRepository;

    @Override
    public List<Demand> getAllDemands() {
        return demandRepository.findAll();
    }

    @Override
    public boolean deleteDemand(Demand demand) {
        try {
            if (demand != null)
                demandRepository.delete(demand);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateDemand(Demand demand) {
        if (demand != null)
            demandRepository.save(demand);
        return true;
    }

    @Override
    public Demand getDemandByUseridAndBookid(long userid, String bookid) {
        return demandRepository.findByUserIdAndBookId(userid, bookid);
    }

    @Override
    public boolean addDemand(Demand demandToAdd) {
        try {
            // Make sure that current user doesn't have such demand
            Demand demand = getDemandByUseridAndBookid(demandToAdd.getUser().getId(), demandToAdd.getBookId());
            if (demand == null)
                demandRepository.save(demandToAdd);
            else System.out.println("Demand: " + demand  + " is already exists");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
