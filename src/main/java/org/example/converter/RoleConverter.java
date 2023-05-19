package org.example.converter;

import org.example.dto.RoleDto;
import org.example.entity.RoleEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleConverter {

    public RoleDto toDto (RoleEntity entity) {
        return toDto(new RoleDto(), entity);
    }

    public RoleDto toDto (RoleDto dto, RoleEntity entity) {
        dto.setId(entity.getId());
        dto.setCode (entity.getCode());
        dto.setName(entity.getName());
        return dto;
    }

    public List<RoleDto> toDto (List<RoleEntity> entities) {
        List<RoleDto> dtos = new ArrayList<>();
        for (RoleEntity entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }
}
