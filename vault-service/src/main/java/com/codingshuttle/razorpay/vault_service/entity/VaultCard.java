package com.codingshuttle.razorpay.vault_service.entity;

import com.codingshuttle.razorpay.common_lib.entity.BaseEntity;
import com.codingshuttle.razorpay.common_lib.enums.CardBrand;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vault_card")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaultCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 4)
    private String lastFour;

    @Column(nullable = false, length = 6)
    private String bin; // first 6 digits

    @Column(nullable = false)
    private byte[] encryptedPan;

    @Column(nullable = false)
    private byte[] encryptedDek;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CardBrand brand; // VISA, RUPAY

    @Column(nullable = false)
    private String expiryMonth;

    @Column(nullable = false)
    private String expiryYear;

    @Column(nullable = false)
    private String cardHolderName;

    private LocalDateTime deletedAt;
}

