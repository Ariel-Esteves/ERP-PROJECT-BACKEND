package com.example.finance.Repositories;

import com.example.finance.models.entities.CarteiraMovimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraMovimentoRepository extends JpaRepository<CarteiraMovimentoEntity, Long> {
}
