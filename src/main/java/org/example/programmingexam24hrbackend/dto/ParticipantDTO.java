package org.example.programmingexam24hrbackend.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParticipantDTO {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String club;
    private List<Long> disciplineIds;
    private List<ResultDTO> results;

}
