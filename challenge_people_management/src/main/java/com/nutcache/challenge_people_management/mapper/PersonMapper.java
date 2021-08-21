package com.nutcache.challenge_people_management.mapper;

import com.nutcache.challenge_people_management.dto.PersonDTO;
import com.nutcache.challenge_people_management.dto.update.PersonUpdateDTO;
import com.nutcache.challenge_people_management.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "birthDate", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "startDate", dateFormat = "dd-MM-yyyy")
    Person toModel(PersonDTO personDTO);

    @Mapping(target = "birthDate", dateFormat = "dd-MM-yyyy")
    @Mapping(target = "startDate", dateFormat = "dd-MM-yyyy")
    PersonDTO toDTO(Person person);

//    @Mapping(target = "birthDate", dateFormat = "dd-MM-yyyy")
//    @Mapping(target = "startDate", dateFormat = "dd-MM-yyyy")
//    Person toModel(PersonUpdateDTO personUpdateDTO);
}
