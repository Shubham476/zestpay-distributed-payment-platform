package com.codingshuttle.razorpay.payment_service.processor;


import com.codingshuttle.razorpay.common_lib.dto.PaymentProcessorRequest;
import com.codingshuttle.razorpay.common_lib.dto.PaymentProcessorResponse;

public interface PaymentProcessor {

    PaymentProcessorResponse charge(PaymentProcessorRequest request);

}
