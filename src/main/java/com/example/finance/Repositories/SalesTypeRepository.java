package com.example.finance.Repositories;

import com.example.finance.models.entities.SaleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesTypeRepository extends JpaRepository<SaleTypeEntity, Long> {
}
