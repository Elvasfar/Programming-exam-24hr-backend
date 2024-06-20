package org.example.programmingexam24hrbackend.service;

import org.example.programmingexam24hrbackend.configuration.ResourceNotFoundException;
import org.example.programmingexam24hrbackend.dto.ResultDTO;
import org.example.programmingexam24hrbackend.entity.Discipline;
import org.example.programmingexam24hrbackend.entity.Participant;
import org.example.programmingexam24hrbackend.entity.Result;
import org.example.programmingexam24hrbackend.repository.DisciplineRepository;
import org.example.programmingexam24hrbackend.repository.ParticipantRepository;
import org.example.programmingexam24hrbackend.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<ResultDTO> getAllResults() {
        return resultRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ResultDTO> getResultById(Long id) {
        return resultRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ResultDTO createResult(ResultDTO resultDTO) {
        Result result = convertToEntity(resultDTO);

        Participant participant = participantRepository.findById(resultDTO.getParticipantId())
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + resultDTO.getParticipantId()));
        result.setParticipant(participant);

        Discipline discipline = disciplineRepository.findById(resultDTO.getDisciplineId())
                .orElseThrow(() -> new ResourceNotFoundException("Discipline not found with id: " + resultDTO.getDisciplineId()));
        result.setDiscipline(discipline);

        result = resultRepository.save(result);
        return convertToDTO(result);
    }

    public ResultDTO updateResult(Long id, ResultDTO resultDTO) {
        Result existingResult = resultRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found with id: " + id));

        existingResult.setResultValue(resultDTO.getResultValue());
        existingResult.setResultType(resultDTO.getResultType());

        Participant participant = participantRepository.findById(resultDTO.getParticipantId())
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + resultDTO.getParticipantId()));
        existingResult.setParticipant(participant);

        Discipline discipline = disciplineRepository.findById(resultDTO.getDisciplineId())
                .orElseThrow(() -> new ResourceNotFoundException("Discipline not found with id: " + resultDTO.getDisciplineId()));
        existingResult.setDiscipline(discipline);

        existingResult = resultRepository.save(existingResult);
        return convertToDTO(existingResult);
    }

    public void deleteResult(Long id) {
        if (!resultRepository.existsById(id)) {
            throw new ResourceNotFoundException("Result not found with id: " + id);
        }
        resultRepository.deleteById(id);
    }

    private ResultDTO convertToDTO(Result result) {
        ResultDTO dto = new ResultDTO();
        dto.setId(result.getId());
        dto.setResultValue(result.getResultValue());
        dto.setResultType(result.getResultType());
        dto.setParticipantId(result.getParticipant().getId());
        dto.setDisciplineId(result.getDiscipline().getId());
        // Optionally, set participant and discipline details in the DTO if needed
        return dto;
    }

    private Result convertToEntity(ResultDTO dto) {
        Result result = new Result();
        result.setResultValue(dto.getResultValue());
        result.setResultType(dto.getResultType());
        // Participant and discipline should be handled separately
        return result;
    }
}
