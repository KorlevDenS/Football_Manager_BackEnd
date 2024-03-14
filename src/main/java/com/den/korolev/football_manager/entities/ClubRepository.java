package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClubRepository extends JpaRepository<Club, Integer> {

    @Query(value = """
            select club.* from club inner join club_management on club.id = club_management.id_club
            where club.id = :id_club and club_management.id_config = :UID"""
            , nativeQuery = true)
    Club findByIdAndAdmin(@Param("id_club") Integer id_club, @Param("UID") Integer UID);

    @Query(value = """
                    select * from club where id
                    in (select id_club from club_membership where id_player = :UID)""", nativeQuery = true)
    List<Club> findParticipationClubs(@Param("UID") Integer UID);

    @Query(value = """
                    select * from club where id
                    in (select id_club from club_management where id_config = :UID)""", nativeQuery = true)
    List<Club> findManagementClubs(@Param("UID") Integer UID);
}