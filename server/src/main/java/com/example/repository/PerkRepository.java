package com.example.repository;

import com.example.model.Perk;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PerkRepository extends CrudRepository<Perk, Long> {

    @Query("SELECT p FROM Perk p WHERE p.geographicArea IN :geographicAreas")
    List<Perk> findByGeographicAreas(@Param("geographicAreas") List<String> geographicAreas);

    @Query("SELECT DISTINCT p.geographicArea FROM Perk p")
    List<String> findDistinctGeographicAreas();

    @Query("SELECT p FROM Perk p WHERE p.membership IN (:memberships)")
    List<Perk> findByMemberships(@Param("memberships") List<String> memberships);

    @Query("SELECT DISTINCT p.membership FROM Perk p")
    List<String> findDistinctMemberships();

    Iterable<Perk> findAllByOrderByNameAsc();
    Iterable<Perk> findAllByOrderByNameDesc();
    Iterable<Perk> findAllByOrderByUpvotesDesc();
    Iterable<Perk> findAllByOrderByDownvotesDesc();
    Iterable<Perk> findAllByOrderByExpiryDateAsc();
    Iterable<Perk> findAllByOrderByExpiryDateDesc();

    @Query("SELECT DISTINCT p.product FROM Perk p")
    List<String> findDistinctProducts();

    @Query("SELECT p FROM Perk p WHERE p.product IN :products")
    List<Perk> findByProducts(@Param("products") List<String> products);
}



