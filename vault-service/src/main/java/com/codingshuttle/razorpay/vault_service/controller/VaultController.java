package com.codingshuttle.razorpay.vault_service.controller;

import com.codingshuttle.razorpay.common_lib.context.MerchantContext;
import com.codingshuttle.razorpay.vault_service.dto.request.TokenizeRequest;
import com.codingshuttle.razorpay.vault_service.dto.response.TokenizeResponse;
import com.codingshuttle.razorpay.vault_service.service.VaultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vault")
public class VaultController {

    private final VaultService vaultService;
    private final MerchantContext merchantContext;

    @PostMapping("/tokenize")
    public ResponseEntity<TokenizeResponse> tokenize(@Valid @RequestBody TokenizeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vaultService.tokenize(request, merchantContext.getMerchantId()));
    }
}
