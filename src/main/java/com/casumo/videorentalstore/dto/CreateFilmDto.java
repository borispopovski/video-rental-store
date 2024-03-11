package com.casumo.videorentalstore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "message" })
public class CreateFilmDto {

    private String message;

}
