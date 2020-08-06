package com.oklimenko.payment.service;

import com.oklimenko.payment.dto.PaymentSystemEvent;
import com.oklimenko.payment.exception.DuplicatedIdempotenceKeyValidationException;
import com.oklimenko.payment.model.PaymentEntity;
import com.oklimenko.payment.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Component
public class KafkaPaymentConsumer {

    private final Random random = new Random();
    private final PaymentRepository paymentRepository;

    @SneakyThrows
    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.groupId}",
            containerFactory = "newPaymentKafkaListenerContainerFactory")
    public void listen(PaymentSystemEvent message) {
        // this timeout is      emulation of external payment system processing
        Thread.sleep(500 + random.nextInt(1000));
        System.out.println("Received Message: " + message);

        Optional<PaymentEntity> paymentEntityNullable = paymentRepository.findById(message.getPaymentId());

        PaymentEntity paymentEntity = paymentEntityNullable.orElseThrow(
                () -> new RuntimeException("Could not find paymet in listerner by id=" + message.getPaymentId()));
        paymentEntity.setStatus(message.getStatus().name());
        paymentRepository.save(paymentEntity);
    }
}
