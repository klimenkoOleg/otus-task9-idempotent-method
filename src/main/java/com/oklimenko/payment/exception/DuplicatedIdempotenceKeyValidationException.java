package com.oklimenko.payment.exception;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
public class DuplicatedIdempotenceKeyValidationException extends PaymentServiceException {

  private final UUID idempotenceKey;

  public DuplicatedIdempotenceKeyValidationException(UUID idempotenceKey) {
    super("Payment with idempotence key " + idempotenceKey + " has already been submitted.");
    this.idempotenceKey = idempotenceKey;
  }

}