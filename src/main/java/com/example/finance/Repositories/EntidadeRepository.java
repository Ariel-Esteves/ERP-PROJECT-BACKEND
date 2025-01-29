package com.example.finance.Repositories;

import com.example.finance.models.entities.EntidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadeRepository extends JpaRepository<EntidadeEntity, Long> {
}
