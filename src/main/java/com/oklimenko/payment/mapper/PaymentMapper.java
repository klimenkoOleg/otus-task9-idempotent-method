package com.oklimenko.payment.mapper;

import com.oklimenko.payment.dto.NewPaymentDto;
import com.oklimenko.payment.model.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentEntity createPaymentRequestToEntity(NewPaymentDto newPaymentDto);
}
