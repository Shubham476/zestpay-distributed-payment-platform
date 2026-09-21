package com.codingshuttle.razorpay.operations_service.entity;

import com.codingshuttle.razorpay.common_lib.entity.BaseEntity;
import com.codingshuttle.razorpay.common_lib.enums.WebhookEventStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "webhook_event")
@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class WebhookEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID merchantId;

    @Column(nullable = false, length = 100)
    private String eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> payload;

    @Column(nullable = false)
    private String targetUrl;

    @Column(nullable = false)
    private String signature;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private WebhookEventStatus status;

    @Column(nullable = false)
    @Builder.Default
    private Integer attempts = 0;

    private LocalDateTime nextRetryAt;

    private LocalDateTime lastAttemptAt;

    private Integer lastResponseCode;

    @Column(length = 1000)
    private String lastResponseBody;

    private LocalDateTime deliveredAt;

}
