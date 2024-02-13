package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends JpaRepository<Match, Long> {

    @Query(value = """
            select c from Match c where
            (c.id in (select pm.match.id from PlayerMatch pm where pm.player.id = :UID)) and
            (c.collectiveEvent.id = :CE_ID)""")
    Match findByUserAndEvent(@Param("CE_ID") Integer id, @Param("UID") Long UID);
}
