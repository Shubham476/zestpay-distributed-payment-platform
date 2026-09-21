package com.codingshuttle.razorpay.merchant_service.controller;

import com.codingshuttle.razorpay.merchant_service.dto.request.LoginRequest;
import com.codingshuttle.razorpay.merchant_service.dto.request.MerchantSignupRequest;
import com.codingshuttle.razorpay.merchant_service.dto.response.LoginResponse;
import com.codingshuttle.razorpay.merchant_service.dto.response.MerchantResponse;
import com.codingshuttle.razorpay.merchant_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<MerchantResponse> signup(@RequestBody @Valid MerchantSignupRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                authService.signup(request)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(
                authService.login(request)
        );
    }

}
