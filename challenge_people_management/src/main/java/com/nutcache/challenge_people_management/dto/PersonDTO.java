package com.nutcache.challenge_people_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    @NotBlank(message = "{parameter.missing}")
    private String name;

    @NotBlank(message = "{parameter.missing}")
    private String birthDate;

    @NotBlank(message = "{parameter.missing}")
    @Email(message = "{parameter.format.invalid}")
    private String email;

    @NotBlank(message = "{parameter.missing}")
    @CPF(message = "{parameter.format.invalid}")
    private String cpf;

    @NotBlank(message = "{parameter.missing}")
    private String startDate = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now());

    private String team;

    private GenderDTO gender;
}
