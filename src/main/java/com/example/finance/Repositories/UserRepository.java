package com.example.finance.Repositories;

import com.example.finance.models.entities.UserEntity;
import com.example.finance.models.entities.VendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
