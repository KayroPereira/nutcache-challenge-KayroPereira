package com.nutcache.challenge_people_management.controller;

import com.nutcache.challenge_people_management.dto.GenderDTO;
import com.nutcache.challenge_people_management.service.GenderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gender")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GenderController {

    private GenderService genderService;

    @GetMapping
    public List<GenderDTO> findAll(){
        return genderService.findAll();
    }
}
