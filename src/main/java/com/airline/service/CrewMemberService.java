package com.airline.service;

import com.airline.model.CrewMember;
import com.airline.repository.CrewMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewMemberService {
    private CrewMemberRepository crewMemberRepository;

    public CrewMemberService(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    public List<CrewMember> getCrewByFlight(Long flightId) {
        return crewMemberRepository.findByFlightId(flightId);
    }

    public CrewMember saveCrewMember(CrewMember crewMember) {
        return crewMemberRepository.save(crewMember);
    }

    public void deleteCrewMember(Long id) {
        crewMemberRepository.deleteById(id);
    }
}