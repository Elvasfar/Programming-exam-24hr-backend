package org.example.programmingexam24hrbackend.service;

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

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Optional<Result> getResultById(Long id) {
        return resultRepository.findById(id);
    }

    public Result createResult(Result result) {
        Participant participant = participantRepository.findById(result.getParticipant().getId()).orElseThrow();
        Discipline discipline = disciplineRepository.findById(result.getDiscipline().getId()).orElseThrow();
        result.setParticipant(participant);
        result.setDiscipline(discipline);
        return resultRepository.save(result);
    }

    public Result updateResult(Long id, Result resultDetails) {
        Result result = resultRepository.findById(id).orElseThrow();
        Participant participant = participantRepository.findById(resultDetails.getParticipant().getId()).orElseThrow();
        Discipline discipline = disciplineRepository.findById(resultDetails.getDiscipline().getId()).orElseThrow();
        result.setParticipant(participant);
        result.setDiscipline(discipline);
        result.setResultValue(resultDetails.getResultValue());
        result.setResultType(resultDetails.getResultType());
        return resultRepository.save(result);
    }

    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }
}
