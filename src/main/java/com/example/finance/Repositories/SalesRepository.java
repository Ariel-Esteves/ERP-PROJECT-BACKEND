package com.example.finance.Repositories;

import com.example.finance.models.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface SalesRepository extends JpaRepository<SaleEntity, Long> {
	Optional<ArrayList<SaleEntity>> findByPersonId(long id);
	Optional<ArrayList<SaleEntity>> findByPersonName(String name);
}
