package com.casumo.videorentalstore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "id", "price" })
public class PriceResponseDto {

    private String id;
    private String price;

}
