package com.casumo.videorentalstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.casumo.videorentalstore.entity.Film;

import java.util.Optional;

public interface FilmRepository extends JpaRepository<Film, Long> {

    Optional<Film> findByMovie(String movie);

}
