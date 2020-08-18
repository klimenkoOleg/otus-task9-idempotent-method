package com.oklimenko.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@ToString
public class PaymentHistoryDto {
    List<PaymentFullInfoDto> payments;
}
