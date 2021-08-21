package com.nutcache.challenge_people_management.dto;

import com.nutcache.challenge_people_management.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
    insert into person (gender_id, person_name, person_birth_date, person_email, person_cpf, person_start_date, person_team)
    values (3, 'Kayro Pereira', '09-03-1983', 'kayrofellyxbr@gmail.com', '00999618490', '20-08-2021', 'Dev');
    insert into person (gender_id, person_name, person_birth_date, person_email, person_cpf, person_start_date, person_team)
    values (2, 'Andreza Michelle', '14-05-1981', 'andreza.mcp@gmail.com', '03930242451', '19-08-2021', 'User');

    {
    "name": "Andreza Michelle",
    "birthDate": "14-05-1981",
    "email": "andreza.mcp@gmail.com",
    "cpf": "13886600084",
    "team": "User",
    "gender": {
        "id": 2,
        "description": "Female"
    }
}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String birthDate;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private String startDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());

    private String team;

    private GenderDTO gender;
}
