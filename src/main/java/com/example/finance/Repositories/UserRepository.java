package com.example.finance.Repositories;

import com.example.finance.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserDetails findByLogin(String login);
	UserEntity findUserEntityByLogin(String login);
}
