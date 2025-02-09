package com.example.finance.Repositories;

import com.example.finance.models.entities.MovementWalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementWalletRepository extends JpaRepository<MovementWalletEntity, Long> {
}
