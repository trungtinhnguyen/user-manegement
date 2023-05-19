package org.example.converter;

import org.example.constant.UserConstant;
import org.example.dto.UserUpdateDto;
import org.example.entity.RoleEntity;
import org.example.entity.UserEntity;
import org.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserUpdateMapper {

    private final RoleRepository roleRepository;
    @Autowired
    public UserUpdateMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public UserEntity toEntity (UserUpdateDto dto) {
        return toEntity(new UserEntity(), dto);
    }
    public UserEntity toEntity (UserEntity entity, UserUpdateDto dto) {
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setStatus(UserConstant.ACTIVE);
        if (dto.getRoles() == null || dto.getRoles().isEmpty()) {
            List<String> roleCodes = new ArrayList<>();
            roleCodes.add(UserConstant.CUSTOMER);
            dto.setRoles(roleCodes);
        }
        List<RoleEntity> roles = new ArrayList<>();
        dto.getRoles().forEach(s ->
            roles.add(roleRepository.findOneByCode(s)));
        entity.setRoles(roles);
        return entity;
    }

    public UserUpdateDto toDto (UserEntity entity) {
        return toDto(new UserUpdateDto(), entity);
    }

    public UserUpdateDto toDto (UserUpdateDto dto, UserEntity entity) {
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setPassword(entity.getPassword());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        List<String> roles = new ArrayList<>();
        entity.getRoles().forEach(role -> {
            roles.add(role.getCode());
        });
        dto.setRoles(roles);
        return dto;
    }
}
