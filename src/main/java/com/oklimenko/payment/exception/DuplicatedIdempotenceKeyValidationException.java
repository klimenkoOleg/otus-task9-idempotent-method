package com.oklimenko.payment.exception;

import java.util.UUID;

public class DuplicatedIdempotenceKeyValidationException extends PaymentServiceException {

  public DuplicatedIdempotenceKeyValidationException(UUID idempotenceKey) {
    super("Payment with idempotence key " + idempotenceKey + " has already been submitted.");
  }

}