package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.sql.Time;

public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, Long> {

    @Modifying
    @Query(value = "update player_match set assists = :assists where id = :id_player_match", nativeQuery = true)
    void updateAssists(@Param("id_player_match") Integer id_player_match, @Param("assists") Integer assists);

    @Modifying
    @Query(value = "update player_match set field_time = :field_time where id = :id_player_match", nativeQuery = true)
    void updateFieldTime(@Param("id_player_match") Integer id_player_match, @Param("field_time") Time field_time);

    @Modifying
    @Query(value = "update player_match set role = :role where id = :id_player_match", nativeQuery = true)
    void updateRole(@Param("id_player_match") Integer id_player_match, @Param("role") String role);

    @Modifying
    @Query(value = "update player_match set goals = :goals where id = :id_player_match", nativeQuery = true)
    void updateGoals(@Param("id_player_match") Integer id_player_match, @Param("goals") Integer goals);

    @Modifying
    @Query(value = "update player_match set comments = :comments where id = :id_player_match", nativeQuery = true)
    void updateComments(@Param("id_player_match") Integer id_player_match, @Param("comments") String comments);

    @Modifying
    @Query(value = "update player_match set what_liked = :what_liked where id = :id_player_match", nativeQuery = true)
    void updateWhatLiked(@Param("id_player_match") Integer id_player_match, @Param("what_liked") String what_liked);

    @Modifying
    @Query(value = "update player_match set what_disliked = :what_disliked where id = :id_player_match", nativeQuery = true)
    void updateWhatDisliked(@Param("id_player_match") Integer id_player_match, @Param("what_disliked") String what_disliked);

    @Modifying
    @Query(value = "update player_match set what_to_improve = :what_to_improve where id = :id_player_match", nativeQuery = true)
    void updateWhatToImprove(@Param("id_player_match") Integer id_player_match, @Param("what_to_improve") String what_to_improve);

    @Modifying
    @Query(value = "call delete_match_by_id(:ptID, :UID)", nativeQuery = true)
    void deleteMatchById(@Param("ptID") Integer id, @Param("UID") Integer UID);

    @Procedure
    PlayerMatch find_player_match_by_id(Integer id, Integer UID);

}
