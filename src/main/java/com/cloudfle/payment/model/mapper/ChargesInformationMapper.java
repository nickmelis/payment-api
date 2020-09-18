package com.cloudfle.payment.model.mapper;

import java.util.stream.Collectors;

import com.cloudfle.payment.model.ChargesInformation;

public class ChargesInformationMapper {

  private ChargesInformationMapper() {
    super();
  }

  public static ChargesInformation map(com.cloudfle.payment.domain.Payment domain) {
    if (domain == null) {
      return null;
    }

    return ChargesInformation.builder()
      .withBearerCode(domain.getChargesInformationBearerCode())
      .withReceiverChargesAmount(domain.getChargesInformationReceiverChargesAmount())
      .withReceiverChargesCurrency(domain.getChargesInformationReceiverChargesCurrency())
      .withSenderCharges(domain.getChargesInformationSenderCharges().stream()
        .map(SenderChargeMapper::map)
        .collect(Collectors.toList()))
      .build();
  }
}
