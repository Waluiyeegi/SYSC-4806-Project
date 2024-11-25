package com.example.repository;

import com.example.model.Perk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PerkRepository extends CrudRepository<Perk, Long> {
}
