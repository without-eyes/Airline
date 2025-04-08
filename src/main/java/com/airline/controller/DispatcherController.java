package com.airline.controller;

import com.airline.model.CrewMember;
import com.airline.model.Flight;
import com.airline.service.CrewMemberService;
import com.airline.service.FlightService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CrewMember>> getCrewByFlight(@PathVariable Long flightId) {
        List<CrewMember> crewMemberList = crewMemberService.getCrewByFlight(flightId);
        if (crewMemberList.isEmpty()) {
            return ResponseEntity.status(204).body(null);
        } else {
            return ResponseEntity.status(200).body(crewMemberList);
        }
    }

    @PostMapping("/{flightId}")
    public ResponseEntity<CrewMember> addCrewMember(@PathVariable Long flightId, @RequestBody CrewMember crewMember) {
        Flight flight = flightService.getFlightById(flightId);
        crewMember.setFlight(flight);
        return ResponseEntity.status(201).body(crewMemberService.saveCrewMember(crewMember));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CrewMember> removeCrewMember(@PathVariable Long id) {
        crewMemberService.deleteCrewMember(id);
        return ResponseEntity.status(204).body(null);
    }
}