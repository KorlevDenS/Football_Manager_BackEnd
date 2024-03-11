package com.den.korolev.football_manager.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
@Entity
@Table(name = "player_match")
public class PlayerMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_match", nullable=false)
    @JsonIgnore
    private Match match;

    @ManyToOne
    @JoinColumn(name="id_player", nullable=false)
    @JsonIgnore
    private Player player;

    private Integer goals;
    private Time field_time;
    private String role;
    private Integer assists;
    private String what_liked;
    private String what_disliked;
    private String what_to_improve;
    private String comments;
}
