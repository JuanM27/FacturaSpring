package com.serbatic.facturas.controllers;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.serbatic.facturas.accessingData.Article;
import com.serbatic.facturas.accessingData.DemArt;
import com.serbatic.facturas.accessingData.DemArtKey;
import com.serbatic.facturas.accessingData.Demand;
import com.serbatic.facturas.service.DemArtService;

@Controller
@RequestMapping(path = "/demArt")
public class DemArtController {

  @Autowired
  private DemArtService demArtService;

  @PostMapping(path = "/add")
  public @ResponseBody String addnewDemArt(@RequestParam Article art, @RequestParam Demand demand,
      @RequestParam int amount) {
    DemArt savedDemArt = demArtService.addNewDemArt(art, demand, amount);
    return amount + " of " + art.getName() + "saved in demand with id "
        + savedDemArt.getDemand().getId();

  }


  @PatchMapping(path = "/{id}")
  public ResponseEntity<DemArt> updateDemArtPartially(@PathVariable(value = "id") DemArtKey id,
      @RequestBody DemArt demArtDetails) throws ResourceNotFoundException {
    DemArt updatedDemArt = demArtService.updateDemArtPartially(id, demArtDetails);
    return ResponseEntity.ok(updatedDemArt);
  }


  // This returns a json with the user information
  @GetMapping(path = "/{id}")
  public ResponseEntity<DemArt> findDemArt(@PathVariable(value = "id") DemArtKey id)
      throws ResourceNotFoundException {
    DemArt demArt = demArtService.findDemArt(id);
    return ResponseEntity.ok().body(demArt);
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<DemArt> getAllDemArts() {
    // This returns a JSON or XML with the users
    return demArtService.getAllDemArts();
  }
}
