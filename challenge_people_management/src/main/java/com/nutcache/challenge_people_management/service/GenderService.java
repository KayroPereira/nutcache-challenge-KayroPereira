package com.nutcache.challenge_people_management.service;

import com.nutcache.challenge_people_management.dto.GenderDTO;
import com.nutcache.challenge_people_management.entity.Gender;
import com.nutcache.challenge_people_management.mapper.GenderMapper;
import com.nutcache.challenge_people_management.repository.GenderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GenderService {

    private GenderRepository genderRepository;

    private final GenderMapper genderMapper = GenderMapper.INSTANCE;

    public List<GenderDTO> findAll(){

        List<Gender> genders = genderRepository.findAll();

        return genders.stream()
                .map(genderMapper::toDTO)
                .collect(Collectors.toList());
    }
}
