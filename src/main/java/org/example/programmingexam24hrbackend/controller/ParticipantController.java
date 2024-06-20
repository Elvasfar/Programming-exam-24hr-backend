package org.example.programmingexam24hrbackend.controller;

import org.example.programmingexam24hrbackend.entity.Participant;
import org.example.programmingexam24hrbackend.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public List<Participant> getAllParticipants() {
        return participantService.getAllParticipants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Long id) {
        return participantService.getParticipantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Participant createParticipant(@RequestBody Participant participant) {
        return participantService.createParticipant(participant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participant> updateParticipant(@PathVariable Long id, @RequestBody Participant participantDetails) {
        return ResponseEntity.ok(participantService.updateParticipant(id, participantDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParticipant(@PathVariable Long id) {
        participantService.deleteParticipant(id);
        return ResponseEntity.noContent().build();
    }
}
