package com.oklimenko.payment.controller;

import com.oklimenko.payment.dto.SavedPaymentDto;
import com.oklimenko.payment.dto.ToProcessingPaymentDto;
import com.oklimenko.payment.service.ProcessingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProcessingController {

    private final ProcessingService processingService;

    @PreAuthorize("#oauth2.hasScope('write')")
    @PostMapping("/payment/create")
    public SavedPaymentDto createPayment(@RequestBody ToProcessingPaymentDto payment) {
        return processingService.performPayment(payment);
    }
}
