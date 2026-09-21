package com.codingshuttle.razorpay.payment_service.controller;

import com.codingshuttle.razorpay.common_lib.context.MerchantContext;
import com.codingshuttle.razorpay.payment_service.dto.request.CreateOrderRequest;
import com.codingshuttle.razorpay.payment_service.dto.response.OrderResponse;
import com.codingshuttle.razorpay.payment_service.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final MerchantContext merchantContext;

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid CreateOrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.create(merchantContext.getMerchantId(), request));
    }
}
