package org.example.service;

import org.example.dto.RoleDto;

import java.util.List;

public interface RoleService {

    public RoleDto findOneByCode (String code);
    public List<RoleDto> findAll ();
}
