package com.den.korolev.football_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "player_custom")
public class PlayerCustom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_custom", nullable=false)
    @JsonIgnore
    private Custom custom;

    @ManyToOne
    @JoinColumn(name="id_player", nullable=false)
    @JsonIgnore
    private Player player;

    private String what_liked;
    private String what_disliked;
    private String what_to_improve;
    private String comments;
}
