package org.example.programmingexam24hrbackend.controller;

import org.example.programmingexam24hrbackend.dto.ResultDTO;
import org.example.programmingexam24hrbackend.entity.Result;
import org.example.programmingexam24hrbackend.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @GetMapping
    public List<ResultDTO> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> getResultById(@PathVariable Long id) {
        return resultService.getResultById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResultDTO> createResult(@RequestBody ResultDTO resultDTO) {
        ResultDTO createdResult = resultService.createResult(resultDTO);
        return ResponseEntity.ok(createdResult);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultDTO> updateResult(@PathVariable Long id, @RequestBody ResultDTO resultDTO) {
        ResultDTO updatedResult = resultService.updateResult(id, resultDTO);
        return ResponseEntity.ok(updatedResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return ResponseEntity.noContent().build();
    }
}
