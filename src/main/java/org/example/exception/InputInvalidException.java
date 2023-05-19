package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class InputInvalidException extends Exception{
    private Map<String, Object> causes;

    public InputInvalidException (String message) {
        super(message);
        causes = new HashMap<>();
    }
}
