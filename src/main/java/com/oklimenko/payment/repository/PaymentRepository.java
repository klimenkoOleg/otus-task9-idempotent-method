package com.oklimenko.payment.repository;

import com.oklimenko.payment.model.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity, UUID> {
    Optional<PaymentEntity> findByIdempotancyKey(UUID idempotancyKey);
}
