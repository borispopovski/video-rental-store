package com.casumo.videorentalstore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "movie", "movieType", "price" })
public class FilmDto {

    private String movie;
    private String movieType;
    private int price;

}
