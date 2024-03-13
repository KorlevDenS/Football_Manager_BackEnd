package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    @Modifying
    @Query(value = """
            update application set club_approve = :mode where id in
            (select application.id from club_management inner join application
            on club_management.id_club = application.id_club
            where club_management.id_config = :UID and club_management.id_club = :id_club
            and application.id = :id_app)"""
            , nativeQuery = true)
    void updateClubApplyMode(@Param("id_club") Integer id_club, @Param("id_app") Integer id_app,
                          @Param("mode") Integer mode, @Param("UID") Integer UID);

    @Query(value = "select * from application where id_player = :id_player", nativeQuery = true)
    List<Application> findAllByIdPlayer(@Param("id_player") Integer id_player);

    @Query(value = """
            select application.* from application left join club_management
            on application.id_club = club_management.id_club
            where application.id_club = :id_club and club_management.id_config = :UID"""
            ,nativeQuery = true)
    List<Application> findAllByIdClub(@Param("id_club") Integer id_club, @Param("UID") Integer UID);

}
