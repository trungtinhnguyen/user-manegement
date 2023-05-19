package org.example.service;

import org.example.dto.PageableDto;
import org.example.dto.UserUpdateDto;
import org.example.dto.response.ResponseDto;
import org.example.exception.InputInvalidException;
import org.example.exception.UsernameIsExistsException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<ResponseDto> findOneByUsername (String username);
    ResponseEntity<ResponseDto> findAll (PageableDto pageableDto);
    ResponseEntity<ResponseDto> findOne (long id);

    ResponseEntity<ResponseDto> save (UserUpdateDto user) throws UsernameIsExistsException;
    ResponseEntity<ResponseDto> delete (List<Long> ids) throws InputInvalidException;
}
