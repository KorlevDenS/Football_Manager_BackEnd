package com.den.korolev.football_manager.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "training")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_collective_event", referencedColumnName = "id")
    @JsonIgnore
    private CollectiveEvent collectiveEvent;

    private String type;
    private Integer players_amount;
    private String field_format;

    @OneToMany(mappedBy="training")
    @JsonIgnore
    private Set<PlayerTraining> playerTrainings;

    @OneToMany(mappedBy="training")
    @JsonIgnore
    private Set<TrainingTarget> playerTrainingTargets;
}
