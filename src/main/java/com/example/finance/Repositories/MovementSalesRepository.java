package com.example.finance.Repositories;

import com.example.finance.models.entities.MovementSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementSalesRepository extends JpaRepository<MovementSaleEntity, Long> {
}
