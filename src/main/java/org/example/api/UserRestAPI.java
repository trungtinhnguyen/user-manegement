package org.example.api;

import org.example.dto.PageableDto;
import org.example.dto.UserUpdateDto;
import org.example.dto.response.ResponseDto;
import org.example.exception.InputInvalidException;
import org.example.exception.UsernameIsExistsException;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController (value = "userRestAPI")
public class UserRestAPI {

    private final UserService userService;

    public UserRestAPI (UserService userService) {
        this.userService = userService;
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping (value = "/get-all-users")
    public ResponseEntity<ResponseDto> getAllUsers (@RequestBody @Valid PageableDto pageable) {
        return userService.findAll(pageable);
    }
    @GetMapping (value = "/get-user")
    public ResponseEntity<ResponseDto> getUser (@RequestBody Long id) {
        return userService.findOne(id);
    }

    @PostMapping(value = "/create-user")
    @PutMapping (value = "/updated-user")
    @ResponseBody
    public ResponseEntity<ResponseDto> createUser (@RequestBody @Valid UserUpdateDto dto)
    throws UsernameIsExistsException {
        return userService.save(dto);
    }

    @PutMapping (value = "/update-user")
    public ResponseEntity<ResponseDto> updateUser (@RequestBody @Valid UserUpdateDto dto)
    throws UsernameIsExistsException{
        return userService.save(dto);
    }

    @DeleteMapping (value = "/delete-user")
    public ResponseEntity<ResponseDto> deleteUser (@RequestBody @NotNull List<Long> ids) throws InputInvalidException {
        return userService.delete(ids);
    }
}
