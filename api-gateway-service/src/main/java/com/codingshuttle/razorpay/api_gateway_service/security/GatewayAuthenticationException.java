package com.codingshuttle.razorpay.api_gateway_service.security;

public class GatewayAuthenticationException extends RuntimeException {
    public GatewayAuthenticationException(String message) {
        super(message);
    }
}
