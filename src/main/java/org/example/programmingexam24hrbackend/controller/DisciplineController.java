package org.example.programmingexam24hrbackend.controller;

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
    public List<Discipline> getAllDisciplines() {
        return disciplineService.getAllDisciplines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Discipline> getDisciplineById(@PathVariable Long id) {
        return disciplineService.getDisciplineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Discipline createDiscipline(@RequestBody Discipline discipline) {
        return disciplineService.createDiscipline(discipline);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Discipline> updateDiscipline(@PathVariable Long id, @RequestBody Discipline disciplineDetails) {
        return ResponseEntity.ok(disciplineService.updateDiscipline(id, disciplineDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscipline(@PathVariable Long id) {
        disciplineService.deleteDiscipline(id);
        return ResponseEntity.noContent().build();
    }
}
