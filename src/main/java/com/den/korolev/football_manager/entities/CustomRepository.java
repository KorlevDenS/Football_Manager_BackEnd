package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomRepository extends JpaRepository<Custom, Long> {

    @Modifying
    @Query(value = "update custom set name = :name where id = :id_custom", nativeQuery = true)
    void updateName(@Param("id_custom") Integer id_custom, @Param("name") String name);

    @Query(value = """
            select c from Custom c where
            (c.id in (select pc.custom.id from PlayerCustom pc where pc.player.id = :UID)) and
            (c.collectiveEvent.id = :CE_ID)""")
    Custom findByUserAndEvent(@Param("CE_ID") Integer id, @Param("UID") Long UID);

    @Query(value = """
            select custom.* from custom inner join collective_event on custom.id_collective_event = collective_event.id
            inner join club_event on club_event.id_collective_event = collective_event.id
            inner join club_management on club_management.id_club = club_event.id_club
            where custom.id_collective_event = :CE_ID
            and club_event.id_club = :id_club and club_management.id_config = :UID"""
            , nativeQuery = true)
    Custom findByClubAndEvent(@Param("CE_ID") Integer eventId, @Param("id_club") Integer id_club, @Param("UID") Long UID);
}
