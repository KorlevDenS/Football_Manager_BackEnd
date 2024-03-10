package com.den.korolev.football_manager.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserConfigRepository extends JpaRepository<UserConfig, Long> {

    @Modifying
    @Query(value = "update config set login = :login where id = :UID", nativeQuery = true)
    void updateLogin(@Param("UID") Integer UID, @Param("login") String login);

    @Modifying
    @Query(value = "update config set password = :password where id = :UID", nativeQuery = true)
    void updatePassword(@Param("UID") Integer UID, @Param("password") String password);

    @Modifying
    @Query(value = "update config set username = :username where id = :UID", nativeQuery = true)
    void updateUsername(@Param("UID") Integer UID, @Param("username") String username);

    Optional<UserConfig> findByUsername(String username);
    Optional<UserConfig> findByLogin(String login);

    Boolean existsByUsername(String username);
    Boolean existsByLogin(String email);

}
