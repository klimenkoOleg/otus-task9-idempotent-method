package com.oklimenko.payment.service;

import com.oklimenko.payment.dto.ClientListRequestDto;
import com.oklimenko.payment.dto.PaymentHistoryDto;
import com.oklimenko.payment.dto.SavedPaymentDto;
import com.oklimenko.payment.dto.ToProcessingPaymentDto;

public interface ProcessingService {
    SavedPaymentDto performPayment(ToProcessingPaymentDto payment);
    PaymentHistoryDto paymentHistory(ClientListRequestDto client);
    PaymentHistoryDto paymentHistoryCached(ClientListRequestDto client);
}
