package org.example.programmingexam24hrbackend.service;

import org.example.programmingexam24hrbackend.entity.Participant;
import org.example.programmingexam24hrbackend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public Participant updateParticipant(Long id, Participant participantDetails) {
        Participant participant = participantRepository.findById(id).orElseThrow();
        participant.setName(participantDetails.getName());
        participant.setGender(participantDetails.getGender());
        participant.setAge(participantDetails.getAge());
        participant.setClub(participantDetails.getClub());
        return participantRepository.save(participant);
    }

    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
}
