package com.den.korolev.football_manager.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_collective_event", referencedColumnName = "id")
    @JsonIgnore
    private CollectiveEvent collectiveEvent;

    private String team1;
    private String team2;
    private Integer team1_goals;
    private Integer team2_goals;
    private String field_format;
    private String result;

    @OneToMany(mappedBy="match")
    @JsonIgnore
    private Set<PlayerMatch> playerMatches;
}
