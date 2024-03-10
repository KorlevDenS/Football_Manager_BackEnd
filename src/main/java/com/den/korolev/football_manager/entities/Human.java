package com.den.korolev.football_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "human")
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;
    private String sex;
    private String passport_id;

    @JsonIgnore
    @OneToOne(mappedBy = "human")
    private Player player;

    @JsonIgnore
    @OneToMany(mappedBy = "id_founder")
    private Set<Club> clubs = new LinkedHashSet<>();

}
