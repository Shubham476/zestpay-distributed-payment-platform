package com.codingshuttle.razorpay.vault_service.service;


import com.codingshuttle.razorpay.common_lib.dto.PaymentProcessorResponse;
import com.codingshuttle.razorpay.common_lib.entity.Money;
import com.codingshuttle.razorpay.vault_service.dto.request.TokenizeRequest;
import com.codingshuttle.razorpay.vault_service.dto.response.TokenizeResponse;

import java.util.Map;
import java.util.UUID;

public interface VaultService {

    TokenizeResponse tokenize(TokenizeRequest request, UUID merchantId);

    PaymentProcessorResponse charge(UUID paymentId, String token, Money amount, Map<String, Object> methodDetails);
}
