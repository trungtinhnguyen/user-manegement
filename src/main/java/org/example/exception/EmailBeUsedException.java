package org.example.exception;

import lombok.Getter;

@Getter
public class EmailBeUsedException extends Exception {

    private String emailBeUsed;
    public EmailBeUsedException (String message, String emailBeUsed) {
        super(message);
        this.emailBeUsed = emailBeUsed;
    }
}
