package com.serbatic.facturas.service;

import com.serbatic.facturas.accessingData.Article;
import com.serbatic.facturas.accessingData.Demand;
import com.serbatic.facturas.accessingData.User;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.time.LocalDate;

public interface DemandServiceInterface {
    Demand addNewDemand(String date, User user);

    Demand updateDemandPartially(Long artId, Demand demDetails) throws ResourceNotFoundException;

    Demand findDemand(Long demId) throws ResourceNotFoundException;

    void deleteDemand(Long id);

    Iterable<Demand> getAllDemands();
}
