package com.casumo.videorentalstore.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class FilmLastDate {

    private String film;
    private Date lastDate;
}
