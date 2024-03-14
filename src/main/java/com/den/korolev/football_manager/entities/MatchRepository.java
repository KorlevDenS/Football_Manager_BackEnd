package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Modifying
    @Query(value = "update match set field_format = :field_format where id = :id_match", nativeQuery = true)
    void updateFieldFormat(@Param("id_match") Integer id_match, @Param("field_format") String field_format);

    @Modifying
    @Query(value = "update match set team1 = :team1 where id = :id_match", nativeQuery = true)
    void updateTeam1(@Param("id_match") Integer id_match, @Param("team1") String team1);

    @Modifying
    @Query(value = "update match set team2 = :team2 where id = :id_match", nativeQuery = true)
    void updateTeam2(@Param("id_match") Integer id_match, @Param("team2") String team2);

    @Modifying
    @Query(value = "update match set team1_goals = :team1_goals where id = :id_match", nativeQuery = true)
    void updateTeam1Goals(@Param("id_match") Integer id_match, @Param("team1_goals") Integer team1_goals);

    @Modifying
    @Query(value = "update match set team2_goals = :team2_goals where id = :id_match", nativeQuery = true)
    void updateTeam2Goals(@Param("id_match") Integer id_match, @Param("team2_goals") Integer team2_goals);

    @Modifying
    @Query(value = "update match set result = :result where id = :id_match", nativeQuery = true)
    void updateResult(@Param("id_match") Integer id_match, @Param("result") String result);

    @Query(value = """
            select c from Match c where
            (c.id in (select pm.match.id from PlayerMatch pm where pm.player.id = :UID)) and
            (c.collectiveEvent.id = :CE_ID)""")
    Match findByUserAndEvent(@Param("CE_ID") Integer id, @Param("UID") Long UID);

    @Query(value = """
            select match.* from match inner join collective_event on match.id_collective_event = collective_event.id
            inner join club_event on club_event.id_collective_event = collective_event.id
            inner join club_management on club_management.id_club = club_event.id_club
            where match.id_collective_event = :CE_ID
            and club_event.id_club = :id_club and club_management.id_config = :UID"""
            , nativeQuery = true)
    Match findByClubAndEvent(@Param("CE_ID") Integer eventId, @Param("id_club") Integer id_club, @Param("UID") Long UID);
}
