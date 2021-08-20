package com.nutcache.challenge_people_management.mapper;

import com.nutcache.challenge_people_management.dto.GenderDTO;
import com.nutcache.challenge_people_management.entity.Gender;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenderMapper {

    GenderMapper INSTANCE = Mappers.getMapper(GenderMapper.class);

    Gender toModel (GenderDTO genderDTO);

    GenderDTO toDTO(Gender gender);
}