package org.example.programmingexam24hrbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    private Integer age;
    private String club;

    @OneToMany(mappedBy = "participant")
    private List<Result> results;

}
