package com.den.korolev.football_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "custom")
public class Custom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_collective_event", referencedColumnName = "id")
    @JsonIgnore
    private CollectiveEvent collectiveEvent;

    private String name;

    @OneToMany(mappedBy="custom")
    @JsonIgnore
    private Set<PlayerCustom> playerCustoms;
}
