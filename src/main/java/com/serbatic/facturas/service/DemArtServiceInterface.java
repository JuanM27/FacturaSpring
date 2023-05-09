package com.serbatic.facturas.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import com.serbatic.facturas.accessingData.DemArt;
import com.serbatic.facturas.accessingData.DemArtKey;

public interface DemArtServiceInterface {

  DemArt addNewDemArt(Long art, Long dem, int amount);

  DemArt updateDemArtPartially(DemArtKey demArtKey, DemArt demArtDetails)
      throws ResourceNotFoundException;

  DemArt findDemArt(DemArtKey demArtId) throws ResourceNotFoundException;

  void deleteDemArt(DemArtKey id);

  Iterable<DemArt> getAllDemArts();
}
