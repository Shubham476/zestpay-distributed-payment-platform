package com.codingshuttle.razorpay.common_lib.ratelimit;

public interface RateLimiter {

    RateLimitResult check(String key, int maxRequestAllowed, long windowSeconds);

}
