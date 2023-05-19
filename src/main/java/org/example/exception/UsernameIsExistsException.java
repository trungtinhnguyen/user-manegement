package org.example.exception;

import lombok.Getter;

@Getter
public class UsernameIsExistsException extends Exception{
    private String usernameExisted;
    public UsernameIsExistsException (String message, String usernameExisted) {
        super(message);
        this.usernameExisted = usernameExisted;
    }
}
