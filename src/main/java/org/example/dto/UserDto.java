package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto extends BaseDto{

    private String username;
    private String fullName;
    private String email;
    private List<String> roles;
}
