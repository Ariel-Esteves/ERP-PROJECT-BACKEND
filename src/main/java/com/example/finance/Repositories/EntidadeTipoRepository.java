package com.example.finance.Repositories;

import com.example.finance.models.entities.EntidadeTipoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntidadeTipoRepository extends JpaRepository<EntidadeTipoEntity, Long> {

}