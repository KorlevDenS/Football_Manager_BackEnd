package com.den.korolev.football_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(name = "id_config")
    private Long id;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_human", referencedColumnName = "id")
    private Human human;

    private String role;
    private Double height;
    private Double weight;

    @JsonIgnore
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_config")
    private UserConfig userConfig;

    @JsonIgnore
    @OneToMany(mappedBy="player")
    private Set<PlayerTraining> playerTrainings;

    @JsonIgnore
    @OneToMany(mappedBy="player")
    private Set<PlayerMatch> playerMatches;

    @JsonIgnore
    @OneToMany(mappedBy="player")
    private Set<PlayerCustom> playerCustoms;

    @JsonIgnore
    @OneToMany(mappedBy="player")
    private Set<Exercise> playerExercises;

    @JsonIgnore
    @OneToMany(mappedBy = "id_player")
    private Set<Application> applications = new LinkedHashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "id_player")
    private Set<ClubMembership> clubMemberships = new LinkedHashSet<>();

}