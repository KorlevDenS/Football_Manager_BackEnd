package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainingRepository extends JpaRepository<Training, Long> {

    @Query(value = """
            select c from Training c where
            (c.id in (select pt.training.id from PlayerTraining pt where pt.player.id = :UID)) and
            (c.collectiveEvent.id = :CE_ID)""")
    Training findByUserAndEvent(@Param("CE_ID") Integer id, @Param("UID") Long UID);
}
