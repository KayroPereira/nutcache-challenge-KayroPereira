package com.nutcache.challenge_people_management.repository;

import com.nutcache.challenge_people_management.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Long> {
}
