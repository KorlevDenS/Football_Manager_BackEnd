package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface PlayerTrainingRepository extends JpaRepository<PlayerTraining, Long> {

    @Modifying
    @Query(value = "update player_training set goals = :goals where id = :id_player_training", nativeQuery = true)
    void updateGoals(@Param("id_player_training") Integer id_player_training, @Param("goals") Integer goals);

    @Modifying
    @Query(value = "update player_training set comments = :comments where id = :id_player_training", nativeQuery = true)
    void updateComments(@Param("id_player_training") Integer id_player_training, @Param("comments") String comments);

    @Modifying
    @Query(value = "update player_training set what_liked = :what_liked where id = :id_player_training", nativeQuery = true)
    void updateWhatLiked(@Param("id_player_training") Integer id_player_training, @Param("what_liked") String what_liked);

    @Modifying
    @Query(value = "update player_training set what_disliked = :what_disliked where id = :id_player_training", nativeQuery = true)
    void updateWhatDisliked(@Param("id_player_training") Integer id_player_training, @Param("what_disliked") String what_disliked);

    @Modifying
    @Query(value = "update player_training set what_to_improve = :what_to_improve where id = :id_player_training", nativeQuery = true)
    void updateWhatToImprove(@Param("id_player_training") Integer id_player_training, @Param("what_to_improve") String what_to_improve);

    @Modifying
    @Query(value = "call delete_training_by_id(:ptID, :UID)", nativeQuery = true)
    void deleteTrainingById(@Param("ptID") Integer id, @Param("UID") Integer UID);

    @Procedure
    PlayerTraining find_player_training_by_id(Integer id, Integer UID);

}
