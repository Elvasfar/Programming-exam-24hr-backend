package org.example.programmingexam24hrbackend.service;

import org.example.programmingexam24hrbackend.configuration.ResourceNotFoundException;
import org.example.programmingexam24hrbackend.dto.DisciplineDTO;
import org.example.programmingexam24hrbackend.entity.Discipline;
import org.example.programmingexam24hrbackend.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<DisciplineDTO> getAllDisciplines() {
        return disciplineRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DisciplineDTO> getDisciplineById(Long id) {
        return disciplineRepository.findById(id)
                .map(this::convertToDTO);
    }

    public DisciplineDTO createDiscipline(DisciplineDTO disciplineDTO) {
        Discipline discipline = convertToEntity(disciplineDTO);
        discipline = disciplineRepository.save(discipline);
        return convertToDTO(discipline);
    }

    public DisciplineDTO updateDiscipline(Long id, DisciplineDTO disciplineDTO) {
        Discipline existingDiscipline = disciplineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Discipline not found with id: " + id));

        existingDiscipline.setDisciplineName(disciplineDTO.getDisciplineName());

        existingDiscipline = disciplineRepository.save(existingDiscipline);
        return convertToDTO(existingDiscipline);
    }

    public void deleteDiscipline(Long id) {
        if (!disciplineRepository.existsById(id)) {
            throw new ResourceNotFoundException("Discipline not found with id: " + id);
        }
        disciplineRepository.deleteById(id);
    }

    private DisciplineDTO convertToDTO(Discipline discipline) {
        DisciplineDTO dto = new DisciplineDTO();
        dto.setId(discipline.getId());
        dto.setDisciplineName(discipline.getDisciplineName());
        // Optionally, set participants and results in the DTO if needed
        return dto;
    }

    private Discipline convertToEntity(DisciplineDTO dto) {
        Discipline discipline = new Discipline();
        discipline.setDisciplineName(dto.getDisciplineName());
        // Participants and results should be handled separately
        return discipline;
    }
}
