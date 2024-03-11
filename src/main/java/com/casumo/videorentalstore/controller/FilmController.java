package com.casumo.videorentalstore.controller;

import com.casumo.videorentalstore.dto.*;
import com.casumo.videorentalstore.service.IFilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("film")
public class FilmController {

    private IFilmService filmService;

    public FilmController(IFilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<FilmDto>> getFilms() {
        log.info("Reading all movies");
        return new ResponseEntity<>(filmService.getFilms(), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<CreateFilmDto> addFilms(@RequestBody List<FilmDto> films) {
        log.info("Creating a film store");
        return new ResponseEntity<>(filmService.addFilms(films), HttpStatus.OK);
    }

    @PostMapping(value = "/rent")
    public ResponseEntity<PriceResponseDto> rentFilms(@RequestBody List<PriceRequestDto> priceRequestsDto) {
        log.info("Calculating a price when renting films");
        return new ResponseEntity<>(filmService.rentFilms(priceRequestsDto), HttpStatus.OK);
    }

    @GetMapping(value = "/return/{id}")
    public ResponseEntity<PenaltyPriceResponseDto> returnFilms(@PathVariable("id") String id) {
        log.info("Calculating a price when returning films");
        return new ResponseEntity<>(filmService.returnFilms(id), HttpStatus.OK);
    }

}
