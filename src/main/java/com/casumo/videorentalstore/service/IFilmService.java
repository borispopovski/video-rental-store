package com.casumo.videorentalstore.service;

import com.casumo.videorentalstore.dto.*;

import java.util.List;

public interface IFilmService {

    List<FilmDto> getFilms();
    CreateFilmDto addFilms(List<FilmDto> films);
    PriceResponseDto rentFilms(List<PriceRequestDto> priceRequestsDto);
    PenaltyPriceResponseDto returnFilms(String id);
}
