package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Long> {

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
