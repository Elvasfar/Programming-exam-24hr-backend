package org.example.programmingexam24hrbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String disciplineName;

    @OneToOne(mappedBy = "discipline")
    private Result result;

}
