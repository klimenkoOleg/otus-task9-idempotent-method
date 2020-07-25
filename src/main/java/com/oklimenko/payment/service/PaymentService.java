package com.oklimenko.payment.service;

import com.oklimenko.payment.dto.NewPaymentDto;
import com.oklimenko.payment.dto.SuccessPaymentDto;

import java.util.UUID;

public interface PaymentService {
    SuccessPaymentDto performPayment(UUID idempotancyKey, NewPaymentDto payment);
}
