package com.example.finance.Repositories;

import com.example.finance.models.entities.TipoVendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVendaRepository extends JpaRepository<TipoVendaEntity, Long> {
}
