package org.example.programmingexam24hrbackend.dto;

import lombok.Data;

@Data
public class DisciplineDTO {
    private Long id;
    private String disciplineName;
    private ResultDTO result;

    // Getters and setters
}
