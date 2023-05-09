package com.serbatic.facturas.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.serbatic.facturas.accessingData.Article;
import com.serbatic.facturas.accessingData.DemArt;
import com.serbatic.facturas.accessingData.DemArtKey;
import com.serbatic.facturas.accessingData.DemArtRepository;
import com.serbatic.facturas.accessingData.Demand;

@Service
public class DemArtService implements DemArtServiceInterface {
  @Autowired
  private DemArtRepository demArtRepository;

  @Autowired
  private DemandService demandService;
  @Autowired
  private ArticleService articleService;

  @Override
  public DemArt addNewDemArt(Long art, Long demId, int amount) {
    DemArt demArt = new DemArt();
    Demand demand = demandService.findDemand(demId);
    Article article = articleService.findArticle(art);
    demArt.setAmount(amount);
    demArt.setArticle(article);
    demArt.setDemand(demand);
    DemArtKey demArtKey = new DemArtKey();
    demArtKey.setIdArt(article.getId());
    demArtKey.setIdDemand(demand.getId());
    demArt.setId(demArtKey);
    return demArtRepository.save(demArt);
  }

  @Override
  public DemArt updateDemArtPartially(DemArtKey demArtKey, DemArt demArtDetails)
      throws ResourceNotFoundException {
    DemArt demArt = demArtRepository.findById(demArtKey)
        .orElseThrow(() -> new ResourceNotFoundException("Demand not found on :: " + demArtKey));
    if (demArtDetails.getArticle() != null) {
      demArt.setArticle(demArtDetails.getArticle());
    }

    if (demArtDetails.getDemand() != null) {
      demArt.setDemand(demArtDetails.getDemand());
    }

    if (demArtDetails.getAmount() > 0) {
      demArt.setAmount(demArtDetails.getAmount());
    }
    return demArtRepository.save(demArt);
  }

  @Override
  public DemArt findDemArt(DemArtKey demArtId) throws ResourceNotFoundException {
    return demArtRepository.findById(demArtId)
        .orElseThrow(() -> new ResourceNotFoundException("DemArt not found on : " + demArtId));
  }

  @Override
  public void deleteDemArt(DemArtKey id) {
    demArtRepository.deleteById(id);
  }

  @Override
  public Iterable<DemArt> getAllDemArts() {
    return demArtRepository.findAll();
  }
}
