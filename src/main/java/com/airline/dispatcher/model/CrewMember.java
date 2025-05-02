package com.airline.dispatcher.model;

import com.airline.admin.model.Flight;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "CrewMembers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CrewMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Role")
    private String role;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    @JsonBackReference
    private Flight flight;
}