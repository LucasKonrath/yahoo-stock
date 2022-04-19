package org.acme.stocks.repository;

import org.acme.stocks.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<StockEntity, Long> {

    List<StockEntity> findAllByCodeContains(String code);

    List<StockEntity> findAllByStockContains(String stock);
}