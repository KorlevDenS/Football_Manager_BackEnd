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
}
