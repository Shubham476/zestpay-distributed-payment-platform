package com.codingshuttle.razorpay.vault_service.dto.response;


import com.codingshuttle.razorpay.common_lib.enums.CardBrand;

public record TokenizeResponse(
        String token,
        String lastFour,
        CardBrand brand,
        Integer expiryMonth,
        Integer expiryYear
) {
}
