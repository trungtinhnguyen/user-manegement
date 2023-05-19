package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.util.List;

@Setter
@Getter
public class UserUpdateDto extends BaseDto{

    @NotBlank
    @Pattern (regexp = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$",
            message = "username should have least 5 characters, don't contains whitespace")
    private String username;
    @NotBlank
    @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[a-zA-Z0-9\\s])(\\S){8,16}$"
            , message = "password must have 8->16 character, contain number, lower and upper, special character")
    private String password;
    @Pattern(regexp = "^(?=.*\\w).{10,}", message = "full name should not have special characters")
    private String fullName;

    @NotEmpty
    @Email (message = "must be email form")
    private String email;

    private List<String> roles;
}
