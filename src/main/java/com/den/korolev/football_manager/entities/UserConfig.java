package com.den.korolev.football_manager.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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

    @OneToOne(mappedBy = "userConfig", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Player player;
}
