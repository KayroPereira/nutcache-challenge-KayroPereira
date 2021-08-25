package com.nutcache.challenge_people_management.dto.update;

import com.nutcache.challenge_people_management.dto.GenderDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
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
public class PersonUpdateDTO {

    private Long id;

    private String name;

    private String birthDate;

    @Email
    private String email;

    @CPF
    private String cpf;

    private String startDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());

    private String team;

    private GenderDTO gender;
}
