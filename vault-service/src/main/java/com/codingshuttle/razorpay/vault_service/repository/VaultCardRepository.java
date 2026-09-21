package com.codingshuttle.razorpay.vault_service.repository;

import com.codingshuttle.razorpay.vault_service.entity.VaultCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VaultCardRepository extends JpaRepository<VaultCard, UUID> {
}
