package com.den.korolev.football_manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "id_config")
    private Long id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_human", referencedColumnName = "id")
    private Human human;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_config")
    private UserConfig userConfig;

    @OneToMany(mappedBy="player")
    private Set<PlayerTraining> playerTrainings;

    @OneToMany(mappedBy="player")
    private Set<PlayerMatch> playerMatches;

    @OneToMany(mappedBy="player")
    private Set<PlayerCustom> playerCustoms;

    @OneToMany(mappedBy="player")
    private Set<Exercise> playerExercises;
}