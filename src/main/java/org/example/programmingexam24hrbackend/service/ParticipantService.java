package org.example.programmingexam24hrbackend.service;

import org.example.programmingexam24hrbackend.configuration.ResourceNotFoundException;
import org.example.programmingexam24hrbackend.dto.ParticipantDTO;
import org.example.programmingexam24hrbackend.entity.Discipline;
import org.example.programmingexam24hrbackend.entity.Participant;
import org.example.programmingexam24hrbackend.repository.DisciplineRepository;
import org.example.programmingexam24hrbackend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<ParticipantDTO> getAllParticipants() {
        return participantRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ParticipantDTO> getParticipantById(Long id) {
        return participantRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ParticipantDTO createParticipant(ParticipantDTO participantDTO) {
        Participant participant = convertToEntity(participantDTO);
        List<Discipline> disciplines = disciplineRepository.findAllById(participantDTO.getDisciplineIds());
        participant.setDisciplines(disciplines);
        participant = participantRepository.save(participant);
        return convertToDTO(participant);
    }

    public ParticipantDTO updateParticipant(Long id, ParticipantDTO participantDTO) {
        Participant existingParticipant = participantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found with id: " + id));

        existingParticipant.setName(participantDTO.getName());
        existingParticipant.setGender(participantDTO.getGender());
        existingParticipant.setAge(participantDTO.getAge());
        existingParticipant.setClub(participantDTO.getClub());

        List<Discipline> disciplines = disciplineRepository.findAllById(participantDTO.getDisciplineIds());
        existingParticipant.setDisciplines(disciplines);

        existingParticipant = participantRepository.save(existingParticipant);
        return convertToDTO(existingParticipant);
    }

    public void deleteParticipant(Long id) {
        if (!participantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Participant not found with id: " + id);
        }
        participantRepository.deleteById(id);
    }

    private ParticipantDTO convertToDTO(Participant participant) {
        ParticipantDTO dto = new ParticipantDTO();
        dto.setId(participant.getId());
        dto.setName(participant.getName());
        dto.setGender(participant.getGender());
        dto.setAge(participant.getAge());
        dto.setClub(participant.getClub());
        dto.setDisciplineIds(participant.getDisciplines().stream().map(Discipline::getId).collect(Collectors.toList()));
        // Optionally, set results in the DTO if needed
        return dto;
    }

    private Participant convertToEntity(ParticipantDTO dto) {
        Participant participant = new Participant();
        participant.setName(dto.getName());
        participant.setGender(dto.getGender());
        participant.setAge(dto.getAge());
        participant.setClub(dto.getClub());
        // Discipline and results should be handled separately
        return participant;
    }
}
