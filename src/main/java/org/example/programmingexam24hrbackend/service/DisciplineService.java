package org.example.programmingexam24hrbackend.service;

import org.example.programmingexam24hrbackend.entity.Discipline;
import org.example.programmingexam24hrbackend.repository.DisciplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {
    @Autowired
    private DisciplineRepository disciplineRepository;

    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    public Optional<Discipline> getDisciplineById(Long id) {
        return disciplineRepository.findById(id);
    }

    public Discipline createDiscipline(Discipline discipline) {
        return disciplineRepository.save(discipline);
    }

    public Discipline updateDiscipline(Long id, Discipline disciplineDetails) {
        Discipline discipline = disciplineRepository.findById(id).orElseThrow();
        discipline.setDisciplineName(disciplineDetails.getDisciplineName());
        return disciplineRepository.save(discipline);
    }

    public void deleteDiscipline(Long id) {
        disciplineRepository.deleteById(id);
    }
}
