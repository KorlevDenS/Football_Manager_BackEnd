package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HumanRepository extends JpaRepository<Human, Long> {

    @Query(value = "select * from human where id in (select id_human from player where id_config = :UID)",
            nativeQuery = true)
    Human getHumanByConfig(@Param("UID") Integer UID);

    @Query(value = """
            select * from human where human.id in (select club.id_founder from club where club.id = :clubId)""",
            nativeQuery = true)
    Human getHumanByClub(@Param("clubId") Integer clubId);

    @Query(value = """
            select * from human where id in
            (select player.id_human from club_membership left join player on club_membership.id_player = player.id_config
            where id_club = :club_id)
            """,
            nativeQuery = true)
    List<Human> findAllByClubId(@Param("club_id") Integer clubId);

    @Modifying
    @Query(value = """
                    update human set name = :name where id in
                    (select human.id from player left join human on player.id_human = human.id
                    where player.id_config = :UID)""", nativeQuery = true)
    void updateName(@Param("UID") Integer UID, @Param("name") String name);

    @Modifying
    @Query(value = """
                     update human set surname = :surname where id in
                    (select human.id from player left join human on player.id_human = human.id
                    where player.id_config = :UID)""", nativeQuery = true)
    void updateSurname(@Param("UID") Integer UID, @Param("surname") String surname);

    @Modifying
    @Query(value = """
                     update human set patronymic = :patronymic where id in
                    (select human.id from player left join human on player.id_human = human.id
                    where player.id_config = :UID)""", nativeQuery = true)
    void updatePatronymic(@Param("UID") Integer UID, @Param("patronymic") String patronymic);

    @Modifying
    @Query(value = """
                    update human set birthday = :birthday where id in
                    (select human.id from player left join human on player.id_human = human.id
                    where player.id_config = :UID)""", nativeQuery = true)
    void updateBirthday(@Param("UID") Integer UID, @Param("birthday") Date birthday);

    @Modifying
    @Query(value = """
                    update human set sex = :sex where id in
                    (select human.id from player left join human on player.id_human = human.id
                    where player.id_config = :UID)""", nativeQuery = true)
    void updateSex(@Param("UID") Integer UID, @Param("sex") String sex);

    @Modifying
    @Query(value = """
                    update human set passport_id = :passport_id where id in
                    (select human.id from player left join human on player.id_human = human.id
                    where player.id_config = :UID)""", nativeQuery = true)
    void updatePassportId(@Param("UID") Integer UID, @Param("passport_id") String passport_id);

}