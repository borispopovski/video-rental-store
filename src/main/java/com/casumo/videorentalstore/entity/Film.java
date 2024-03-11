package com.casumo.videorentalstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "film")
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String movie;
    private String movieType;
    private int price;

}
