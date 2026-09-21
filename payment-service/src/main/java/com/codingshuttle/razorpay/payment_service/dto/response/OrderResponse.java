package com.codingshuttle.razorpay.payment_service.dto.response;


import com.codingshuttle.razorpay.common_lib.entity.Money;
import com.codingshuttle.razorpay.common_lib.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

public record OrderResponse(
        UUID id,
        UUID merchantId,
        UUID customerId,
        String receipt,
        Money amount,
        OrderStatus status,
        Integer attempts,
        Map<String, Object> notes,
        LocalDateTime expiresAt,
        LocalDateTime createdAt
) {
}
