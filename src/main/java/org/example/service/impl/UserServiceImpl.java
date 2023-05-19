package org.example.service.impl;

import org.example.constant.PageableConstant;
import org.example.constant.UserConstant;
import org.example.converter.ResponseMapper;
import org.example.converter.UserMapper;
import org.example.converter.UserUpdateMapper;
import org.example.dto.PageableDto;
import org.example.dto.UserDto;
import org.example.dto.UserUpdateDto;
import org.example.dto.response.ResponseDto;
import org.example.entity.UserEntity;
import org.example.exception.InputInvalidException;
import org.example.exception.UsernameIsExistsException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bcryptEncoder;
    private final ResponseMapper responseMapper;
    private final UserUpdateMapper userUpdateMapper;

    @Autowired
    public UserServiceImpl (UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder bcryptEncoder, ResponseMapper responseMapper, UserUpdateMapper userUpdateMapper) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.responseMapper = responseMapper;
        this.userUpdateMapper = userUpdateMapper;
    }

    @Override
    public ResponseEntity<ResponseDto> findOneByUsername(String username) {
        Optional<UserEntity> userOptional = userRepository.findOneByUsernameAndStatus(username, UserConstant.ACTIVE);

        if (userOptional.isPresent()) {
            UserDto dto = userMapper.toDto(userOptional.get());
            Map<String, Object> data = new HashMap<>();
            data.put("user", dto);
            ResponseDto responseDto = new ResponseDto("Find user successfully", data);
            return ResponseEntity.ok(responseDto);
        }
        throw new UsernameNotFoundException("username "+username+" is not found");
    }

    @Override
    public ResponseEntity<ResponseDto> findAll(PageableDto pageableDto) {
        List<Sort.Order> orders = new ArrayList<>();
        Page<UserEntity> usersPage;
        Pageable pageable;
        pageableDto.getSorts().forEach(s -> {
            String[] sortParams = s.split("_");
            Sort.Order order;
            if (sortParams.length == PageableConstant.SORT_ARGUMENT_SIZE
                && sortParams[1].equalsIgnoreCase(PageableConstant.DESCENDING)) {
                order = new Sort.Order(Sort.Direction.DESC, sortParams[0]);
            } else order = new Sort.Order(Sort.Direction.ASC, sortParams[0]);
            orders.add(order);
        });
        pageable = new PageRequest(pageableDto.getPageNumber(), pageableDto.getSize(), new Sort(orders));
        usersPage = userRepository.findAll(pageable);
        return ResponseEntity.ok(responseMapper.toDto(usersPage));
    }

    @Override
    public ResponseEntity<ResponseDto> findOne(long id) {
        UserEntity entity = userRepository.findOne(id);
        ResponseDto response;
        if (entity != null) {
            UserDto dto = userMapper.toDto(entity);
            Map<String, Object> data = new HashMap<>();
            data.put("user", dto);
            response = new ResponseDto("Found user "+id+" successfully", data);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> save(UserUpdateDto userUpdateDto) throws UsernameIsExistsException {
        UserEntity user;
        String message;
        Map<String, Object> dataResponse = new HashMap<>();
        if (userUpdateDto.getId() == null) {
            Optional<UserEntity> userEntityOptional = userRepository.findOneByUsernameAndStatus(userUpdateDto.getUsername(), UserConstant.ACTIVE);
            if (userEntityOptional.isPresent())
                throw new UsernameIsExistsException("Username was existed", userUpdateDto.getUsername());
            userUpdateDto.setPassword(bcryptEncoder.encode(userUpdateDto.getPassword()));
            user = userUpdateMapper.toEntity(userUpdateDto);
            message = "Created user successfully";
        } else {
            UserEntity old = userRepository.findOne(userUpdateDto.getId());
            if (userUpdateDto.getPassword() == null)
                userUpdateDto.setPassword(old.getPassword());
            else userUpdateDto.setPassword(bcryptEncoder.encode(userUpdateDto.getPassword()));
            user = userUpdateMapper.toEntity(old, userUpdateDto);
            message = "Update user successfully";
        }
        UserUpdateDto savedDto = userUpdateMapper.toDto(userRepository.save(user));
        dataResponse.put("user", savedDto);
        ResponseDto responseDto = new ResponseDto(message,dataResponse);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<ResponseDto> delete(List<Long> ids) throws InputInvalidException {
        List<Long> notExistedIds = new ArrayList<>();
        ids.forEach(id -> {
            if (!userRepository.exists(id))
                notExistedIds.add(id);
        });
        if (notExistedIds.isEmpty()) {
            ids.forEach(userRepository::delete);
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("ids",ids);
            ResponseDto responseDto = new ResponseDto("Deleted Users",responseData);
            ResponseEntity.ok(responseDto);
        }
        InputInvalidException ex = new InputInvalidException("Id's list is not existed");
        ex.getCauses().put("notExistedIds", notExistedIds);
        throw ex;
    }
}
