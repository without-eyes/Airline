package com.airline.dispatcher.controller;

import com.airline.dispatcher.model.CrewMember;
import com.airline.admin.model.Flight;
import com.airline.dispatcher.repository.CrewMemberRepository;
import com.airline.dispatcher.service.CrewMemberService;
import com.airline.admin.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dispatcher/crew")
public class CrewController {
    private final CrewMemberRepository crewMemberRepository;
    private CrewMemberService crewMemberService;
    private FlightService flightService;

    public CrewController(CrewMemberService crewMemberService, FlightService flightService, CrewMemberRepository crewMemberRepository) {
        this.crewMemberService = crewMemberService;
        this.flightService = flightService;
        this.crewMemberRepository = crewMemberRepository;
    }

    @GetMapping
    public ResponseEntity<List<CrewMember>> getCrewMembers(@RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String role) {
        List<CrewMember> crewMemberList = crewMemberService.getAllCrewByFlightWithFilters(name, role);
        if (crewMemberList.isEmpty()) {
            return ResponseEntity.status(204).body(null);
        } else {
            return ResponseEntity.ok(crewMemberList);
        }
    }

    @GetMapping("/{flightId}")
    public ResponseEntity<List<CrewMember>> getCrewByFlight(
            @PathVariable Long flightId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String role) {

        List<CrewMember> crewMemberList = crewMemberService.getCrewByFlightWithFilters(flightId, name, role);
        if (crewMemberList.isEmpty()) {
            return ResponseEntity.status(204).body(null);
        } else {
            return ResponseEntity.ok(crewMemberList);
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