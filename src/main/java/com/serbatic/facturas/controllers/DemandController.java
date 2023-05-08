package com.serbatic.facturas.controllers;

import java.time.LocalDate;
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
import com.serbatic.facturas.accessingData.Demand;
import com.serbatic.facturas.accessingData.DemandRepository;
import com.serbatic.facturas.accessingData.User;

@Controller
@RequestMapping(path = "/demand")
public class DemandController {

  @Autowired // This means to get the bean called demandRepository
  // Which is auto-generated by Spring, we will use it to handle the data
  private DemandRepository demandRepository;

  @PostMapping(path = "/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser(@RequestParam User user) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    Demand n = new Demand();
    LocalDate date = LocalDate.now();
    String dateS = date.toString();
    n.setDate(dateS);
    n.setUser(user);
    demandRepository.save(n);

    return "Demand created";
  }

  @PatchMapping(path = "/{idDemand}") // Map ONLY PATCH Requests
  public ResponseEntity<Demand> updateDemandPartially(
      @PathVariable(value = "idDemand") Long demandId, @RequestBody Demand demandDetails)
      throws ResourceNotFoundException {
    Demand demand = demandRepository.findById(demandId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found on :: " + demandId));

    demand.setDate(demandDetails.getDate());
    demand.setInvoiced(demandDetails.isInvoiced());
    demand.setUser(demandDetails.getUser());
    final Demand updatedUser = demandRepository.save(demand);

    return ResponseEntity.ok(updatedUser);
  }

  // This returns a json with the user information
  @GetMapping(path = "/{idDemand}")
  public ResponseEntity<Demand> findDemand(@PathVariable(value = "idDemand") Long demandId)
      throws ResourceNotFoundException {
    Demand demand = demandRepository.findById(demandId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found on : " + demandId));
    return ResponseEntity.ok().body(demand);
  }
}
