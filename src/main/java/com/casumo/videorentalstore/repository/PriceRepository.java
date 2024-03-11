package com.casumo.videorentalstore.repository;

import com.casumo.videorentalstore.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, String> {
}
