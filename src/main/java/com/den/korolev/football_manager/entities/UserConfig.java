package com.den.korolev.football_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "config")
@Getter
@Setter
public class UserConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Column(unique = true)
    private String login;
    private String role;
    private Date reg_date;

    @JsonIgnore
    @OneToOne(mappedBy = "userConfig", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Player player;

    @JsonIgnore
    @OneToMany(mappedBy = "id_config")
    private Set<ClubManagement> clubManagements = new LinkedHashSet<>();

}
