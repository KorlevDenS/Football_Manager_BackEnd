package com.den.korolev.football_manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

    @OneToOne(mappedBy = "human")
    private Player player;
}
