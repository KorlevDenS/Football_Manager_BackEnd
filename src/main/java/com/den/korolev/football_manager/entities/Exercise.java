package com.den.korolev.football_manager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_player", nullable=false)
    @JsonIgnore
    private Player player;
    private String title;
    private String technic;

    @Transient
    private MultipartFile photo;
    @Transient
    private MultipartFile video;

    @JsonIgnore
    private String photo_link;
    @JsonIgnore
    private String video_link;

    private Time duration;
    private Integer amount;
    private String muscle_load;
    private String equipment;
    private Integer min_people;
    private Integer usage_count;
    private Date date;

    @OneToMany(mappedBy="exercise")
    @JsonIgnore
    private Set<TrainingTarget> playerTrainingTargets;
}
