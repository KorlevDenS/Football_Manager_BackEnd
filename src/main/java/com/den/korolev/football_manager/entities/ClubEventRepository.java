package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ClubEventRepository extends JpaRepository<ClubEvent, Integer> {

    @Query(value = """
            select ce from ClubEvent ce inner join ClubManagement cm on ce.id_club.id = cm.id_club.id
            where cm.id_config.id = :UID and ce.id_club.id = :id_club""")
    List<ClubEvent> getClubEventsByClubAndAdmin(@Param("id_club") Integer id_club, @Param("UID") Integer UID);

    @Query(value = """
            select club_event.* from club_event inner join club_management on club_event.id_club = club_management.id_club
            inner join collective_event on club_event.id_collective_event = collective_event.id
            where club_management.id_config = :UID and club_event.id_club = :id_club
            and collective_event.date >= :begin and collective_event.date <= :end"""
            , nativeQuery = true)
    List<ClubEvent> getClubEventsByTimePeriod(@Param("begin") Date begin, @Param("end") Date end,
                                              @Param("id_club") Integer id_club, @Param("UID") Integer UID);

}