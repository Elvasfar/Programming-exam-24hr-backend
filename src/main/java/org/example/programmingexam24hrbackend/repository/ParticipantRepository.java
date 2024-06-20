package org.example.programmingexam24hrbackend.repository;

import org.example.programmingexam24hrbackend.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
