package com.den.korolev.football_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_player", nullable = false)
    private Player id_player;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_club", nullable = false)
    private Club id_club;

    @Column(name = "player_approve", nullable = false)
    private Integer player_approve;

    @Column(name = "club_approve", nullable = false)
    private Integer club_approve;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creation_date;

    @Column(name = "message", length = 100)
    private String message;

}