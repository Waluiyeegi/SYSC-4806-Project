package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Long> {
    public Membership getMembershipById(Long id);
}