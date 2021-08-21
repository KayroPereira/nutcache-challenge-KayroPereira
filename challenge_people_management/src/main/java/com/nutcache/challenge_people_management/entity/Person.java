package com.nutcache.challenge_people_management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "person_name", nullable = false)
    private String name;

    @Column(name = "person_birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "person_email", nullable = false)
    private String email;

    @Column(name = "person_cpf", nullable = false)
    private String cpf;

    @Column(name = "person_start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "person_team")
    private String team;

    @ManyToOne()
    @JoinColumn(name = "gender_id", referencedColumnName = "gender_id")
    private Gender gender;
}
