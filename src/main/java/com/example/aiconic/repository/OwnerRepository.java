package com.example.aiconic.repository;

import com.example.aiconic.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByWalletAddress(String walletAddress);
}
