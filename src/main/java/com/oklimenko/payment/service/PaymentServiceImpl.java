package com.oklimenko.payment.service;

import com.oklimenko.payment.dto.SavedPaymentDto;
import com.oklimenko.payment.dto.ToProcessingPaymentDto;
import com.oklimenko.payment.exception.DuplicatedIdempotenceKeyValidationException;
import com.oklimenko.payment.mapper.PaymentMapper;
import com.oklimenko.payment.model.PaymentEntity;
import com.oklimenko.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements ProcessingService {

    private static final String PAYMENT_CREATED = "PAYMENT_CREATED";

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @SneakyThrows
    public SavedPaymentDto performPayment(ToProcessingPaymentDto payment) {

        UUID idempotancyKey = payment.getIdempotancyKey();
        Optional<PaymentEntity> paymentEntityToCheckIdempotency =
                paymentRepository.findByIdempotancyKey(idempotancyKey);
        if (paymentEntityToCheckIdempotency.isPresent()) {
            throw new DuplicatedIdempotenceKeyValidationException(idempotancyKey);
        }

        PaymentEntity paymentEntity = paymentMapper.createPaymentRequestToEntity(payment);
        paymentEntity.setStatus(PAYMENT_CREATED);
        paymentEntity.setIdempotancyKey(idempotancyKey);
        paymentEntity.setDateCreated(LocalDateTime.now());
//        paymentEntity.setDateCompleted(LocalDateTime.now());
        PaymentEntity savedPaymentEntity = paymentRepository.save(paymentEntity);

        return new SavedPaymentDto(savedPaymentEntity.getId(), savedPaymentEntity.getDateCreated());
    }

    public void checkBalance() {
        // todo: no ops, this is a stub
    }

   /* @SneakyThrows
    public void send(PaymentEntity paymentEntity) {
        RestTemplate restTemplate = new RestTemplate();
        NewPaymentSystemDto newPaymentSystemDto = paymentMapper.mapToNewPaymentSystemDto(paymentEntity);
        HttpEntity<NewPaymentSystemDto> request = new HttpEntity<>(newPaymentSystemDto);

//        ResponseEntity<Void> response =
        restTemplate.postForEntity(new URI(paymentGateUri), request, Void.class);
//        Void body = response.getBody();
    }*/
}
