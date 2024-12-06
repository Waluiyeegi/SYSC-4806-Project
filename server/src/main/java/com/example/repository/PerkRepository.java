package com.example.repository;

import com.example.model.Perk;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;


@Repository
public interface PerkRepository extends CrudRepository<Perk, Long> {
    @Query("SELECT p FROM Perk p WHERE p.membership IN (:memberships)")
    List<Perk> findByMemberships(@Param("memberships") List<String> memberships);

    @Query("SELECT DISTINCT p.membership FROM Perk p")
    List<String> findDistinctMemberships();
}



