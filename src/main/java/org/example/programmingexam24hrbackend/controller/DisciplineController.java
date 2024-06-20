package org.example.programmingexam24hrbackend.controller;

import org.example.programmingexam24hrbackend.dto.DisciplineDTO;
import org.example.programmingexam24hrbackend.entity.Discipline;
import org.example.programmingexam24hrbackend.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping
    public List<DisciplineDTO> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDTO> getDisciplineById(@PathVariable Long id) {
        return disciplineService.getDisciplineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DisciplineDTO> createDiscipline(@RequestBody DisciplineDTO disciplineDTO) {
        DisciplineDTO createdDiscipline = disciplineService.createDiscipline(disciplineDTO);
        return ResponseEntity.ok(createdDiscipline);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplineDTO> updateDiscipline(@PathVariable Long id, @RequestBody DisciplineDTO disciplineDTO) {
        DisciplineDTO updatedDiscipline = disciplineService.updateDiscipline(id, disciplineDTO);
        return ResponseEntity.ok(updatedDiscipline);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }
}
