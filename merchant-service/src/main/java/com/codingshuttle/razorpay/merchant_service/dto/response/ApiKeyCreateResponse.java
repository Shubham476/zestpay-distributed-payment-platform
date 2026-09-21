package com.codingshuttle.razorpay.merchant_service.dto.response;


import com.codingshuttle.razorpay.common_lib.enums.Environment;

import java.util.UUID;

public record ApiKeyCreateResponse(
        UUID id,
        String keyId,
        String keySecret,
        Environment environment
) {
}
