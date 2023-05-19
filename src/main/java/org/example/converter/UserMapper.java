package org.example.converter;

import org.example.dto.UserDto;
import org.example.entity.RoleEntity;
import org.example.entity.UserEntity;
import org.example.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    private final RoleConverter roleConverter;
    private final RoleRepository roleRepository;

    @Autowired
    public UserMapper(RoleConverter roleConverter, RoleRepository roleRepository) {
        this.roleConverter = roleConverter;
        this.roleRepository = roleRepository;
    }

    public UserDto toDto (UserEntity entity) {
        return toDto(new UserDto(), entity);
    }

    public UserDto toDto (UserDto dto, UserEntity entity) {
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        List<String> roles = new ArrayList<>();
        for (RoleEntity role : entity.getRoles())
            roles.add(role.getCode());
        dto.setRoles(roles);
        return dto;
    }
    public List<UserDto> toDto (List<UserEntity> entities) {
        List<UserDto> dtos = new ArrayList<>();
        for (UserEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

}
