package com.airline.service;

import com.airline.model.Flight;
import com.airline.repository.FlightRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {
    private FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> searchFlights(String destination, String departureTime) {
        return flightRepository.findAll().stream()
                .filter(f -> destination == null || f.getDestination().equalsIgnoreCase(destination))
                .filter(f -> departureTime == null || f.getDepartureTime().equalsIgnoreCase(departureTime))
                .collect(Collectors.toList());
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight updates) {
        return flightRepository.findById(id).map(existingFlight -> {
            if (updates.getDestination() != null) {
                existingFlight.setDestination(updates.getDestination());
            }
            if (updates.getDepartureTime() != null) {
                existingFlight.setDepartureTime(updates.getDepartureTime());
            }
            if (updates.getDepartureTime() != null) {
                existingFlight.setDepartureTime(updates.getDepartureTime());
            }
            return flightRepository.save(existingFlight);
        }).orElse(null);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}