package com.serbatic.facturas.service;

import com.serbatic.facturas.accessingData.DemArt;
import org.apache.velocity.exception.ResourceNotFoundException;

public interface DemArtServiceInterface {

    DemArt updateDemArtPartially(Long userId, DemArt userDetails) throws ResourceNotFoundException;

    DemArt findDemArt(Long userId) throws ResourceNotFoundException;

    void deleteEmployee(Long id);

    Iterable<DemArt> getAllDemArts();
}
