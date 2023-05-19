package org.example.converter;

import org.example.dto.UserDto;
import org.example.dto.response.ResponseDto;
import org.example.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ResponseMapper {

    private final UserMapper userMapper;

    @Autowired
    public ResponseMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ResponseDto toDto (Page<UserEntity> userPage) {
        List<UserDto> dtos = userMapper.toDto(userPage.getContent());
        Map<String, Object> data = new HashMap<>();
        data.put("users", dtos);
        data.put("totalPages", userPage.getTotalPages());
        data.put("size", userPage.getSize());
        data.put("totalElements", userPage.getTotalElements());
        data.put("page", userPage.getNumber());
        data.put("numberOfElements", userPage.getNumberOfElements());
        return new ResponseDto("Find All User Successfully", data);
    }
}
