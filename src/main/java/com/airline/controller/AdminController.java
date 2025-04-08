package com.airline.controller;

import com.airline.model.Flight;
import com.airline.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/flights")
public class AdminController {
    private FlightService flightService;

    public AdminController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flightList = flightService.getAllFlights();
        if (flightList.isEmpty()) {
            return ResponseEntity.status(204).body(null);
        } else {
            return ResponseEntity.status(200).body(flightList);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Flight foundFlight = flightService.getFlightById(id);
        if (foundFlight != null) {
            return ResponseEntity.status(200).body(foundFlight);
        } else {
            return ResponseEntity.status(204).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return ResponseEntity.status(201).body(flightService.saveFlight(flight));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        Flight updatedFlight = flightService.updateFlight(id, flight);
        if (updatedFlight != null) {
            return ResponseEntity.status(200).body(updatedFlight);
        } else {
            return ResponseEntity.status(202).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Flight> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.status(204).body(null);
    }
}