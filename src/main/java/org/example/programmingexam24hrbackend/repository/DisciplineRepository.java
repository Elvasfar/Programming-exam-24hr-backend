package org.example.programmingexam24hrbackend.repository;

import org.example.programmingexam24hrbackend.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
}
