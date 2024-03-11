package com.den.korolev.football_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "collective_event")
public class CollectiveEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private String location;
    private Date date;
    private Time time;
    private Time duration;
    private String description;


    @OneToOne(mappedBy = "collectiveEvent")
    @JsonIgnore
    private Training training;

    @OneToOne(mappedBy = "collectiveEvent")
    @JsonIgnore
    private Match match;

    @OneToOne(mappedBy = "collectiveEvent")
    @JsonIgnore
    private Custom custom;

}
