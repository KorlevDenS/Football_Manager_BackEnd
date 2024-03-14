package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Modifying
    @Query(value = "update training set field_format = :field_format where id = :id_training", nativeQuery = true)
    void updateFieldFormat(@Param("id_training") Integer id_training, @Param("field_format") String field_format);

    @Modifying
    @Query(value = "update training set type = :type where id = :id_training", nativeQuery = true)
    void updateType(@Param("id_training") Integer id_training, @Param("type") String type);

    @Modifying
    @Query(value = "update training set players_amount = :players_amount where id = :id_training", nativeQuery = true)
    void updatePlayersAmount(@Param("id_training") Integer id_training, @Param("players_amount") Integer players_amount);

    @Query(value = """
            select c from Training c where
            (c.id in (select pt.training.id from PlayerTraining pt where pt.player.id = :UID)) and
            (c.collectiveEvent.id = :CE_ID)""")
    Training findByUserAndEvent(@Param("CE_ID") Integer id, @Param("UID") Long UID);

    @Query(value = """
            select training.* from training inner join collective_event on training.id_collective_event = collective_event.id
            inner join club_event on club_event.id_collective_event = collective_event.id
            inner join club_management on club_management.id_club = club_event.id_club
            where training.id_collective_event = :CE_ID
            and club_event.id_club = :id_club and club_management.id_config = :UID"""
            , nativeQuery = true)
    Training findByClubAndEvent(@Param("CE_ID") Integer eventId, @Param("id_club") Integer id_club, @Param("UID") Long UID);
}
