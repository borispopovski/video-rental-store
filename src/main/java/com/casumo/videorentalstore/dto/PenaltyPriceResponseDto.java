package com.casumo.videorentalstore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "price" })
public class PenaltyPriceResponseDto {

    private String price;
}
