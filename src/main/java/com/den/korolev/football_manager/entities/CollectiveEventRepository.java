package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface CollectiveEventRepository extends JpaRepository<CollectiveEvent, Long> {

    @Modifying
    @Query(value = "update collective_event set location = :location where id = :id_event", nativeQuery = true)
    void updateLocation(@Param("id_event") Integer id_event, @Param("location") String location);

    @Modifying
    @Query(value = "update collective_event set date = :date where id = :id_event", nativeQuery = true)
    void updateDate(@Param("id_event") Integer id_event, @Param("date") Date date);

    @Modifying
    @Query(value = "update collective_event set time = :time where id = :id_event", nativeQuery = true)
    void updateTime(@Param("id_event") Integer id_event, @Param("time") Time time);

    @Modifying
    @Query(value = "update collective_event set duration = :duration where id = :id_event", nativeQuery = true)
    void updateDuration(@Param("id_event") Integer id_event, @Param("duration") Time duration);

    @Modifying
    @Query(value = "update collective_event set description = :description where id = :id_event", nativeQuery = true)
    void updateDescription(@Param("id_event") Integer id_event, @Param("description") String description);

    @Query(value = """
            select c from CollectiveEvent c where
                (c.id in (select match.collectiveEvent from Match match where match.id in (select player_match.match from PlayerMatch player_match where player_match.player.id = :UID))) or
                (c.id in (select training.collectiveEvent from Training training where training.id in (select player_training.training from PlayerTraining player_training where player_training.player.id = :UID))) or
                (c.id in (select custom.collectiveEvent from Custom custom where custom.id in (select player_custom.custom from PlayerCustom player_custom where player_custom.player.id = :UID)))""")
    List<CollectiveEvent> findAllByUserID(@Param("UID") Long UID);

    @Query(value = """
            select id, type, location, date, time, duration, description from collective_event where
                ((id in (select id_collective_event from match where id in (select id_match from player_match where id_player = :UID))) or
                (id in (select id_collective_event from training where id in (select id_training from player_training where id_player = :UID))) or
                (id in (select id_collective_event from custom where id in (select id_custom from player_custom where id_player = :UID))))
                and date >= :begin and date <= :end""", nativeQuery = true)
    List<CollectiveEvent> findAllByTimePeriod(@Param("UID") Long UID, @Param("begin") Date begin, @Param("end") Date end);


}
