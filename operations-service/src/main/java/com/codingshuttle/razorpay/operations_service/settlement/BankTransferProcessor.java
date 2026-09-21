package com.codingshuttle.razorpay.operations_service.settlement;

import com.codingshuttle.razorpay.common_lib.entity.Money;
import com.codingshuttle.razorpay.operations_service.settlement.dto.BankTransferResult;

import java.util.UUID;

public interface BankTransferProcessor {

    BankTransferResult initiate(UUID settlementId, UUID merchantId, Money amount,
                                String bankAccount, String ifsc);
}
