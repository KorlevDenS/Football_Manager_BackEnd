package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query(value = """
            select id, team1, team2, team1_goals, team2_goals, field_format, result from Match where
            (id in (select id_match from player_match where id_player = :UID)) and
            (id_collective_event = :CE_ID)""", nativeQuery = true)
    Match findByUserAndEvent(@Param("CE_ID") Long id, @Param("UID") Long UID);


}
