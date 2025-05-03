package com.airline.dispatcher.service;

import com.airline.admin.model.Flight;
import com.airline.dispatcher.model.CrewMember;
import com.airline.dispatcher.repository.CrewMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrewMemberService {
    private CrewMemberRepository crewMemberRepository;

    public CrewMemberService(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    public List<CrewMember> getAllCrewByFlightWithFilters(String name, String role, boolean isAvailable) {
        return crewMemberRepository.findAll().stream()
                .filter(cm -> name == null || cm.getName().equalsIgnoreCase(name))
                .filter(cm -> role == null || cm.getRole().equalsIgnoreCase(role))
                .filter(cm -> !isAvailable || cm.getFlight() == null)
                .collect(Collectors.toList());
    }

    public List<CrewMember> getCrewByFlightWithFilters(Long flightId, String name, String role) {
        return crewMemberRepository.findByFlightId(flightId).stream()
                .filter(cm -> name == null || cm.getName().equalsIgnoreCase(name))
                .filter(cm -> role == null || cm.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    public CrewMember getCrewMemberById(Long crewMemberId) {
        return crewMemberRepository.findById(crewMemberId).orElse(null);
    }

    public CrewMember saveCrewMember(CrewMember crewMember) {
        return crewMemberRepository.save(crewMember);
    }

    public void deleteCrewMember(Long id) {
        crewMemberRepository.deleteById(id);
    }
}