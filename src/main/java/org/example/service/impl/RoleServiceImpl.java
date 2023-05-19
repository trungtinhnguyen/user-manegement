package org.example.service.impl;

import org.example.converter.RoleConverter;
import org.example.dto.RoleDto;
import org.example.entity.RoleEntity;
import org.example.repository.RoleRepository;
import org.example.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Autowired
    public RoleServiceImpl (RoleRepository roleRepository, RoleConverter roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }
    @Override
    public RoleDto findOneByCode(String code) {
        RoleEntity entity = roleRepository.findOneByCode (code);
        return roleConverter.toDto(entity);
    }

    @Override
    public List<RoleDto> findAll() {
        List<RoleEntity> entities = roleRepository.findAll();
        return roleConverter.toDto(entities);
    }
}
