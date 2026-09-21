package com.codingshuttle.razorpay.common_lib.context;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@Getter
@Setter
public class MerchantContext {

    private UUID merchantId;
    private String keyId;
}
