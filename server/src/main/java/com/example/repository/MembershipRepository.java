package com.example.repository;

import com.example.model.Membership;
import org.springframework.data.repository.CrudRepository;

public interface MembershipRepository extends CrudRepository<Membership, Integer> {
}
