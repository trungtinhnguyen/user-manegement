package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageableDto {

    private int pageNumber;
    private int size;
    private long offset;

    private List<@Pattern(regexp = "(?=.{3,255}(^\\w+_?\\w+))",
    message = "the pattern must be field_asc or field_desc")
            String> sorts;
}
