package com.oklimenko.payment.mapper;

import com.oklimenko.payment.dto.NewPaymentDto;
import com.oklimenko.payment.dto.NewPaymentSystemDto;
import com.oklimenko.payment.dto.PaymentFullInfoDto;
import com.oklimenko.payment.dto.ToProcessingPaymentDto;
import com.oklimenko.payment.model.PaymentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentEntity createPaymentRequestToEntity(NewPaymentDto newPaymentDto);
    NewPaymentSystemDto mapToNewPaymentSystemDto(PaymentEntity paymentEntity);

    PaymentEntity createPaymentRequestToEntity(ToProcessingPaymentDto payment);

    List<PaymentFullInfoDto> map(List<PaymentEntity> paymentPersistedList);
}
