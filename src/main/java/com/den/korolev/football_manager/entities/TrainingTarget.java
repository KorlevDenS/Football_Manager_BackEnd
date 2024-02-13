package com.den.korolev.football_manager.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "training_target")
public class TrainingTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_training", nullable=false)
    @JsonIgnore
    private Training training;

    @ManyToOne
    @JoinColumn(name="id_exercise", nullable=false)
    @JsonIgnore
    private Exercise exercise;

}
