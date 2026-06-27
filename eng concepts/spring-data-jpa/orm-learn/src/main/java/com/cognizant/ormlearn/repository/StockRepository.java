package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    // Docx 2 - Hands on 2: FB stocks in Sep 2019
    List<Stock> findByCodeAndDateBetween(String code, LocalDate start, LocalDate end);

    // Docx 2 - Hands on 2: GOOGL close > 1250
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal price);

    // Docx 2 - Hands on 2: top 3 highest volume
    List<Stock> findTop3ByOrderByVolumeDesc();

    // Docx 2 - Hands on 2: bottom 3 NFLX (lowest close price)
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
