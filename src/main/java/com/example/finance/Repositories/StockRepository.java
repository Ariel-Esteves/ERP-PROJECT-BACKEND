package com.example.finance.Repositories;

import com.example.finance.models.entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {
	Optional<StockEntity> findByProductId(long id);
}
