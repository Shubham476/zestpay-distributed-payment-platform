package com.codingshuttle.razorpay.common_lib.exception;

import lombok.Getter;

@Getter
public class InvalidStateTransitionException extends RuntimeException {

    private final String fromState;
    private final String toEvent;

    public InvalidStateTransitionException(String fromState, String event) {
        super("Invalid transition from " + fromState + " with event " + event);
        this.fromState = fromState;
        this.toEvent = event;
    }
}
