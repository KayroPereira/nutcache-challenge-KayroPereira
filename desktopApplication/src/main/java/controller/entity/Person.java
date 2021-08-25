package controller.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long id;

    private String name;

    private String birthDate;

    private String email;

    private String cpf;

    private String startDate;

    private String team;

    private Gender gender;
}
