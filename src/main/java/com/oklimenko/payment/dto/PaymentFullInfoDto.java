package com.oklimenko.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@ToString
public class PaymentFullInfoDto {
    private UUID id;
    private long clientId;
    private long serviceId;
    private BigDecimal amount;
    private String clientComment;
    private String status;
    private LocalDateTime dateCreated;
}
