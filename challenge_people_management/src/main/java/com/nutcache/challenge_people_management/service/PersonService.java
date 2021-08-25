package com.nutcache.challenge_people_management.service;

import com.nutcache.challenge_people_management.dto.PersonDTO;
import com.nutcache.challenge_people_management.dto.response.MessageResponseDTO;
import com.nutcache.challenge_people_management.dto.update.PersonUpdateDTO;
import com.nutcache.challenge_people_management.entity.Person;
import com.nutcache.challenge_people_management.exception.PersonNotFoundException;
import com.nutcache.challenge_people_management.mapper.PersonMapper;
import com.nutcache.challenge_people_management.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person person = personMapper.toModel(personDTO);

        return MessageResponseDTO.builder()
                .message(String.format("Create person ID: %d", save(personDTO).getId()))
                .status(HttpStatus.CREATED.value())
                .build();
    }

    public MessageResponseDTO updateByIdPartial(Long id, PersonUpdateDTO personUpdateDTO) throws PersonNotFoundException {
//        Person person = verifyIfExists(id);
//
//        PersonDTO personUpdate = personMapper.toDTO(person);

        PersonDTO personUpdate = personMapper.toDTO(verifyIfExists(id));

        personUpdate.setId(id);

        if(personUpdateDTO.getName() != null) personUpdate.setName(personUpdateDTO.getName());
        if(personUpdateDTO.getBirthDate() != null) personUpdate.setBirthDate(personUpdateDTO.getBirthDate());
        if(personUpdateDTO.getEmail() != null) personUpdate.setEmail(personUpdateDTO.getEmail());
        if(personUpdateDTO.getCpf() != null) personUpdate.setCpf(personUpdateDTO.getCpf());
        if(personUpdateDTO.getTeam() != null) personUpdate.setTeam(personUpdateDTO.getTeam());
        if(personUpdateDTO.getGender() != null) personUpdate.setGender(personUpdateDTO.getGender());

        return MessageResponseDTO.builder()
                .message(String.format("Updated person Id: %d", save(personUpdate).getId()))
                .status(HttpStatus.OK.value())
                .build();
    }

    public MessageResponseDTO updateByIdFull(Long id, PersonDTO personDTO) throws PersonNotFoundException {

        verifyIfExists(id);

        personDTO.setId(id);

        return MessageResponseDTO.builder()
                .message(String.format("Updated person Id: %d", save(personDTO).getId()))
                .status(HttpStatus.OK.value())
                .build();
    }

    public List<PersonDTO> findAll(){
        List<Person> personList = personRepository.findAll();

        return personList.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);

        return personMapper.toDTO(person);
    }

    public MessageResponseDTO deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);

        personRepository.deleteById(id);

        return MessageResponseDTO.builder()
                .message(String.format("Person deleted Id: %d", id))
                .status(HttpStatus.OK.value())
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    private Person save(PersonDTO personDTO){
        return personRepository.save(personMapper.toModel(personDTO));
    }
}
