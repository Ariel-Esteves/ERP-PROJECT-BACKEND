package com.example.finance.Repositories;

import com.example.finance.models.entities.MovementStockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementStockRepository extends JpaRepository<MovementStockEntity, Long> {

}
