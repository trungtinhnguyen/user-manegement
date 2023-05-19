package org.example.repository;

import org.example.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Override
    UserEntity findOne(Long id);
    Optional<UserEntity> findOneByUsernameAndStatus (String username, int status);
}
