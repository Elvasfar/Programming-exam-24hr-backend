package org.example.programmingexam24hrbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class DisciplineDTO {
    private Long id;
    private String disciplineName;
    private List<Long> participantIds;
    private List<ResultDTO> results;
}
