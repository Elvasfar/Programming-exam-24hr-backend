package org.example.programmingexam24hrbackend.dto;

import lombok.Data;

@Data
public class ResultDTO {
    private Long id;
    private Long participantId;
    private Long disciplineId;
    private String resultValue;
    private String resultType;

    // Getters and setters
}
