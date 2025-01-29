package com.example.finance.Repositories;

import com.example.finance.models.entities.VendaMovimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaMovimentoRepository extends JpaRepository<VendaMovimentoEntity, Long> {
}
