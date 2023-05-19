package org.example.repository;


import org.example.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Override
    RoleEntity findOne(Long id);
    RoleEntity findOneByCode (String code);
    @Override
    <S extends RoleEntity> S save(S entity);
}