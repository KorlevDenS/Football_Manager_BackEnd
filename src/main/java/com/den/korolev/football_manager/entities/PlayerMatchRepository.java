package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface PlayerMatchRepository extends JpaRepository<PlayerMatch, Long> {

    @Modifying
    @Query(value = "call delete_match_by_id(:ptID, :UID)", nativeQuery = true)
    void deleteMatchById(@Param("ptID") Integer id, @Param("UID") Integer UID);

    @Procedure
    PlayerMatch find_player_match_by_id(Integer id, Integer UID);

}
