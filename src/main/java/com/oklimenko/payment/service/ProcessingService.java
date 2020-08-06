package com.oklimenko.payment.service;

import com.oklimenko.payment.dto.SavedPaymentDto;
import com.oklimenko.payment.dto.ToProcessingPaymentDto;

public interface ProcessingService {
    SavedPaymentDto performPayment(ToProcessingPaymentDto payment);
}
