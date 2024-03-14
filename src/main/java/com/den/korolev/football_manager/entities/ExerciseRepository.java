package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query(value = """
            select e from Exercise e inner join TrainingTarget tt on e.id = tt.exercise.id
            inner join Training t on t.id = tt.training.id where t.collectiveEvent.id = :event_id""")
    List<Exercise> find_exercises_by_event(@Param("event_id") Integer event_id);

    @Query(value = "select e from Exercise e where e.player.id = :UID")
    List<Exercise> findAllByUserID(@Param("UID") Long UID);

//    @Query(value = """
//            select exercise.* from exercise inner join club_membership on exercise.id_player = club_membership.id_player
//            inner join club_management on club_membership.id_club = club_management.id_club
//            where club_membership.id_club = :id_club and club_management.id_config = :UID"""
//            , nativeQuery = true)
//    List<Exercise> findAllByClub(@Param("id_club") Integer clubId, @Param("UID") Integer UID);

    @Query(value = "select e.photo_link from Exercise e where e.id = :ExID")
    String findPhotoByIdAndPlayer(@Param("ExID") Integer exercise_id);

    @Query(value = "select e.video_link from Exercise e where e.player.id = :UID and e.id = :ExID")
    String findVideoByIdAndPlayer(@Param("ExID") Integer exercise_id, @Param("UID") Long UID);
}
