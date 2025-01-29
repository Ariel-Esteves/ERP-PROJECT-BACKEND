package com.example.finance.Repositories;

import com.example.finance.models.entities.CarteiraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends JpaRepository<CarteiraEntity, Long> {
}
