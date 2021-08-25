package com.nutcache.challenge_people_management.repository;

import com.nutcache.challenge_people_management.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
