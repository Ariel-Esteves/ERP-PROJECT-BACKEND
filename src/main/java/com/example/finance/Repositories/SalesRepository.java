package com.example.finance.Repositories;

import com.example.finance.models.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<SaleEntity, Long> {
}
