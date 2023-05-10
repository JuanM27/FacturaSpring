package com.serbatic.facturas.controllers;

import java.time.LocalDate;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.serbatic.facturas.accessingData.Demand;
import com.serbatic.facturas.accessingData.User;
import com.serbatic.facturas.service.DemandService;

@Controller
@RequestMapping(path = "/demand")
public class DemandController {

  @Autowired
  private DemandService demandService;

  @PostMapping(path = "/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser(@RequestParam User user) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request
    Demand savedDemand = demandService.addNewDemand(LocalDate.now().toString(), user);

    return "Demand saved with id " + savedDemand.getId();
  }

  @PatchMapping(path = "/{idDemand}") // Map ONLY PATCH Requests
  public ResponseEntity<Demand> updateDemandPartially(
      @PathVariable(value = "idDemand") Long demandId, @RequestBody Demand demandDetails)
      throws ResourceNotFoundException {
    Demand demand = demandService.updateDemandPartially(demandId, demandDetails);
    return ResponseEntity.ok(demand);
  }

  // This returns a json with the user information
  @GetMapping(path = "/{idDemand}")
  public ResponseEntity<Demand> findDemand(@PathVariable(value = "idDemand") Long demandId)
      throws ResourceNotFoundException {
    Demand demand = demandService.findDemand(demandId);
    return ResponseEntity.ok().body(demand);
  }

  // Delete

  @DeleteMapping(path = "/{idDemand}")
  public @ResponseBody String deleteDemand(@PathVariable("idDemand") Long demandId) {
    demandService.deleteDemand(demandId);
    return String.format("Demand %d deleted ", demandId);

    // You can add the option of returning the deleted user just in case if you want to create some
    // sort of backup
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Demand> getAllDemands() {
    // This returns a JSON or XML with the users
    return demandService.getAllDemands();
  }
}
