package com.den.korolev.football_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "foundation_date", nullable = false)
    private LocalDate foundation_date;

    @Column(name = "reg_date", nullable = false)
    private LocalDate reg_date;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_founder", nullable = false)
    private Human id_founder;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column(name = "short_name", length = 32)
    private String short_name;

    @Column(name = "description", length = 300)
    private String description;

}