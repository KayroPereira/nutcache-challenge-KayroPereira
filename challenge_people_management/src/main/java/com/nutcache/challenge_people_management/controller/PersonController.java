package com.nutcache.challenge_people_management.controller;

import com.nutcache.challenge_people_management.dto.PersonDTO;
import com.nutcache.challenge_people_management.dto.response.MessageResponseDTO;
import com.nutcache.challenge_people_management.dto.update.PersonUpdateDTO;
import com.nutcache.challenge_people_management.exception.PersonNotFoundException;
import com.nutcache.challenge_people_management.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        return personService.createPerson(personDTO);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateByIdFull(@PathVariable @Valid Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateByIdFull(id, personDTO);
    }

    @PatchMapping("/{id}")
    public MessageResponseDTO updateByIdPartial(@PathVariable @Valid Long id, @RequestBody @Valid PersonUpdateDTO personDTO) throws PersonNotFoundException {
        return personService.updateByIdPartial(id, personDTO);
    }

    @GetMapping
    public List<PersonDTO> findAll(){
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable @Valid Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @DeleteMapping("/{id}")
    public MessageResponseDTO deleteById(@PathVariable @Valid Long id) throws PersonNotFoundException {
        return personService.deleteById(id);
    }
}
