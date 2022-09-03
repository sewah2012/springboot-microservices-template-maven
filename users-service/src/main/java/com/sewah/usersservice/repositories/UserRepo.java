package com.sewah.usersservice.repositories;

import com.sewah.usersservice.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
