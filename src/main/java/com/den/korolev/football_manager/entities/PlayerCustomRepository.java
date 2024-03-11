package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface PlayerCustomRepository extends JpaRepository<PlayerCustom, Long> {

    @Modifying
    @Query(value = "update player_custom set comments = :comments where id = :id_player_custom", nativeQuery = true)
    void updateComments(@Param("id_player_custom") Integer id_player_custom, @Param("comments") String comments);

    @Modifying
    @Query(value = "update player_custom set what_liked = :what_liked where id = :id_player_custom", nativeQuery = true)
    void updateWhatLiked(@Param("id_player_custom") Integer id_player_custom, @Param("what_liked") String what_liked);

    @Modifying
    @Query(value = "update player_custom set what_disliked = :what_disliked where id = :id_player_custom", nativeQuery = true)
    void updateWhatDisliked(@Param("id_player_custom") Integer id_player_custom, @Param("what_disliked") String what_disliked);

    @Modifying
    @Query(value = "update player_custom set what_to_improve = :what_to_improve where id = :id_player_custom", nativeQuery = true)
    void updateWhatToImprove(@Param("id_player_custom") Integer id_player_custom, @Param("what_to_improve") String what_to_improve);

    @Modifying
    @Query(value = "call delete_custom_by_id(:ptID, :UID)", nativeQuery = true)
    void deleteCustomById(@Param("ptID") Integer id, @Param("UID") Integer UID);

    @Procedure
    PlayerCustom find_player_custom_by_id(Integer id, Integer UID);

}
