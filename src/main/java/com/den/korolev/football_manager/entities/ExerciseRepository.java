package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Procedure
    List<Exercise> find_exercises_by_event(Integer event_id, Integer user_id);

    @Query(value = "select e from Exercise e where e.player.id = :UID")
    List<Exercise> findAllByUserID(@Param("UID") Long UID);

    @Query(value = "select e.photo_link from Exercise e where e.player.id = :UID and e.id = :ExID")
    String findPhotoByIdAndPlayer(@Param("ExID") Integer exercise_id, @Param("UID") Long UID);

    @Query(value = "select e.video_link from Exercise e where e.player.id = :UID and e.id = :ExID")
    String findVideoByIdAndPlayer(@Param("ExID") Integer exercise_id, @Param("UID") Long UID);
}
