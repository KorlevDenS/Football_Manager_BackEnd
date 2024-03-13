package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClubMembershipRepository extends JpaRepository<ClubMembership, Integer> {

    @Modifying
    @Query(value = "delete from club_membership where id_club = :id_club and id_player = :UID", nativeQuery = true)
    void deleteByClubAndUser(@Param("UID") Integer UID, @Param("id_club") Integer id_club);

}