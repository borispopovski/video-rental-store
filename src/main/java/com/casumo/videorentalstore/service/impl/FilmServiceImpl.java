package com.casumo.videorentalstore.service.impl;

import com.casumo.videorentalstore.dto.*;
import com.casumo.videorentalstore.entity.Film;
import com.casumo.videorentalstore.entity.FilmLastDate;
import com.casumo.videorentalstore.entity.Price;
import com.casumo.videorentalstore.exception.FilmException;
import com.casumo.videorentalstore.repository.FilmRepository;
import com.casumo.videorentalstore.repository.PriceRepository;
import com.casumo.videorentalstore.service.IFilmService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class FilmServiceImpl implements IFilmService {

    private FilmRepository filmRepository;
    private PriceRepository priceRepository;

    public FilmServiceImpl(FilmRepository filmRepository, PriceRepository priceRepository) {
        this.filmRepository = filmRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public List<FilmDto> getFilms() {
        List<FilmDto> filmsDto = new ArrayList<>();
        try {
            List<Film> films = filmRepository.findAll();
            films.forEach(f -> {
                FilmDto filmDto = new FilmDto();
                filmDto.setMovie(f.getMovie());
                filmDto.setMovieType(f.getMovieType());
                filmDto.setPrice(f.getPrice());
                filmsDto.add(filmDto);
            });
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FilmException(e.getMessage());
        }
        return filmsDto;
    }

    @Override
    public CreateFilmDto addFilms(List<FilmDto> films) {
        CreateFilmDto createFilmDto = new CreateFilmDto();
        try {
            films.forEach(filmDto -> {
                Film film = new Film();
                film.setMovie(filmDto.getMovie());
                film.setMovieType(filmDto.getMovieType());
                film.setPrice(filmDto.getPrice());
                filmRepository.save(film);
            });
            createFilmDto.setMessage("Done");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FilmException(e.getMessage());
        }
        return createFilmDto;
    }

    @Override
    public PriceResponseDto rentFilms(List<PriceRequestDto> priceRequestsDto) {
        PriceResponseDto priceResponseDto = new PriceResponseDto();
        try {
            List<FilmLastDate> filmLastDateList = new ArrayList<>();
            AtomicInteger p = new AtomicInteger();
            priceRequestsDto.forEach(req -> {
                Film film = filmRepository.findByMovie(req.getFilm()).orElseThrow();
                if (film.getMovieType().equals("New release")) {
                    p.addAndGet(film.getPrice() * req.getDay());
                } else if (film.getMovieType().equals("Regular")) {
                    if (req.getDay() > 3) {
                        p.addAndGet(film.getPrice() * (req.getDay() - 3));
                    }
                    p.addAndGet(film.getPrice());
                } else if (film.getMovieType().equals("Old")) {
                    if (req.getDay() > 5) {
                        p.addAndGet(film.getPrice() * (req.getDay() - 5));
                    }
                    p.addAndGet(film.getPrice());
                }
                FilmLastDate filmLastDate = new FilmLastDate();
                filmLastDate.setFilm(req.getFilm());
                LocalDate today = LocalDate.now();
                LocalDate todayPlusDays = today.plusDays(req.getDay());
                Date lastDate = Date.from(todayPlusDays.atStartOfDay(ZoneId.systemDefault()).toInstant());
                filmLastDate.setLastDate(lastDate);
                filmLastDateList.add(filmLastDate);
            });
            priceResponseDto.setId(UUID.randomUUID().toString());
            priceResponseDto.setPrice(p.intValue() + " SEK");
            Price price = new Price();
            price.setId(priceResponseDto.getId());
            price.setPriceValue(p.intValue());
            price.setFilmLastDate(filmLastDateList);
            priceRepository.save(price);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FilmException(e.getMessage());
        }
        return priceResponseDto;
    }

    @Override
    public PenaltyPriceResponseDto returnFilms(String id) {
        PenaltyPriceResponseDto penaltyPriceResponseDto = new PenaltyPriceResponseDto();
        try {
            Price price = priceRepository.findById(id).orElseThrow();
            penaltyPriceResponseDto.setPrice(calculateReturnPrice(price) + " SEK");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FilmException(e.getMessage());
        }
        return penaltyPriceResponseDto;
    }

    private int calculateReturnPrice(Price price) {
        AtomicInteger priceValue = new AtomicInteger();
        price.getFilmLastDate().forEach(fld -> {
            LocalDate localDate = LocalDate.now();
            Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            if (today.after(fld.getLastDate())) {
                int days = Days.daysBetween(new DateTime(fld.getLastDate()), new DateTime(today)).getDays();
                Film film = filmRepository.findByMovie(fld.getFilm()).orElseThrow();
                priceValue.addAndGet(film.getPrice() * days);
            }
        });
        return priceValue.intValue();
    }

}
