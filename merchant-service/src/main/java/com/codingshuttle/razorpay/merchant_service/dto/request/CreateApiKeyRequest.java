package com.codingshuttle.razorpay.merchant_service.dto.request;


import com.codingshuttle.razorpay.common_lib.enums.Environment;

public record CreateApiKeyRequest(
        Environment environment
) {
}
