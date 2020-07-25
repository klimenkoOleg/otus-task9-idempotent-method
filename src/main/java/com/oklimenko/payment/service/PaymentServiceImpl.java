package com.oklimenko.payment.service;

import com.oklimenko.payment.dto.NewPaymentDto;
import com.oklimenko.payment.dto.SuccessPaymentDto;
import com.oklimenko.payment.exception.DuplicatedIdempotenceKeyValidationException;
import com.oklimenko.payment.mapper.PaymentMapper;
import com.oklimenko.payment.model.PaymentEntity;
import com.oklimenko.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private static final String PAYMENT_CREATED = "PAYMENT_CREATED";

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final Random random = new Random();

    @SneakyThrows
    public SuccessPaymentDto performPayment(UUID idempotancyKey, NewPaymentDto payment) {

        Optional<PaymentEntity> paymentEntityToCheckIdempotency =
                paymentRepository.findByIdempotancyKey(idempotancyKey);

        if (paymentEntityToCheckIdempotency.isPresent()) {
            throw new DuplicatedIdempotenceKeyValidationException(idempotancyKey);
        }

        PaymentEntity paymentEntity = paymentMapper.createPaymentRequestToEntity(payment);
        paymentEntity.setStatus(PAYMENT_CREATED);
        paymentEntity.setIdempotancyKey(idempotancyKey);
        paymentEntity.setDateCreated(LocalDateTime.now());
        // this timeout is dirty emulation of external payment system processing
        Thread.sleep(500 + random.nextInt(1000));
        paymentEntity.setDateCompleted(LocalDateTime.now());

        PaymentEntity savedPaymentEntity = paymentRepository.save(paymentEntity);

        return new SuccessPaymentDto(savedPaymentEntity.getId());
    }
}
