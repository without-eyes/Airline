package com.airline.service;

import com.airline.model.CrewMember;
import com.airline.repository.CrewMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrewMemberService {
    private CrewMemberRepository crewMemberRepository;

    public CrewMemberService(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    public List<CrewMember> getCrewByFlightWithFilters(Long flightId, String name, String role) {
        System.out.println("Received request for flightId: " + flightId + ", name: " + name + ", role: " + role);


        return crewMemberRepository.findByFlightId(flightId).stream()
                .filter(cm -> name == null || cm.getName().equalsIgnoreCase(name))
                .filter(cm -> role == null || cm.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    public CrewMember saveCrewMember(CrewMember crewMember) {
        return crewMemberRepository.save(crewMember);
    }

    public void deleteCrewMember(Long id) {
        crewMemberRepository.deleteById(id);
    }
}