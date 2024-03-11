package com.casumo.videorentalstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "price")
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Id
    private String id;
    private int priceValue;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<FilmLastDate> filmLastDate;
}
