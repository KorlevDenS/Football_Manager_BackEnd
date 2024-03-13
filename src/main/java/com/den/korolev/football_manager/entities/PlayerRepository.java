package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query(value = """
            select player.* from club_membership
            inner join club_management on club_membership.id_club = club_management.id_club
            inner join player on club_membership.id_player = player.id_config
            where club_membership.id_club = :id_club and club_management.id_config = :UID"""
            , nativeQuery = true)
    List<Player> getClubParticipants(@Param("UID") Integer UID, @Param("id_club") Integer id_club);

    @Modifying
    @Query(value = "update player set weight = :weight where id_config = :UID", nativeQuery = true)
    void updateWeight(@Param("UID") Integer UID, @Param("weight") Double weight);

    @Modifying
    @Query(value = "update player set height = :height where id_config = :UID", nativeQuery = true)
    void updateHeight(@Param("UID") Integer UID, @Param("height") Double height);

    @Modifying
    @Query(value = "update player set role = :role where id_config = :UID", nativeQuery = true)
    void updateRole(@Param("UID") Integer UID, @Param("role") String role);

}
