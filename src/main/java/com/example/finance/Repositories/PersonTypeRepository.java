package com.example.finance.Repositories;

import com.example.finance.models.entities.PersonTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonTypeRepository extends JpaRepository<PersonTypeEntity, Long> {

}