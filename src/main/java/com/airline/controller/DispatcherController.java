package com.airline.controller;

import com.airline.model.CrewMember;
import com.airline.model.Flight;
import com.airline.service.CrewMemberService;
import com.airline.service.FlightService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispatcher/crew")
public class DispatcherController {
    private CrewMemberService crewMemberService;
    private FlightService flightService;

    public DispatcherController(CrewMemberService crewMemberService, FlightService flightService) {
        this.crewMemberService = crewMemberService;
        this.flightService = flightService;
    }

    @GetMapping("/{flightId}")
    public List<CrewMember> getCrewByFlight(@PathVariable Long flightId) {
        return crewMemberService.getCrewByFlight(flightId);
    }

    @PostMapping("/{flightId}")
    public CrewMember addCrewMember(@PathVariable Long flightId, @RequestBody CrewMember crewMember) {
        Flight flight = flightService.getFlightById(flightId);
        crewMember.setFlight(flight);
        return crewMemberService.saveCrewMember(crewMember);
    }

    @DeleteMapping("/{id}")
    public void removeCrewMember(@PathVariable Long id) {
        crewMemberService.deleteCrewMember(id);
    }
}