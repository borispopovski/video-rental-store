package com.casumo.videorentalstore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "film", "day" })
public class PriceRequestDto {

    private String film;
    private int day;

}
