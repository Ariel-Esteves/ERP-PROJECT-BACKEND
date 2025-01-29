package com.example.finance.Repositories;

import com.example.finance.models.entities.EstoqueMovimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueMovimentoRepository extends JpaRepository<EstoqueMovimentoEntity, Long> {

}
