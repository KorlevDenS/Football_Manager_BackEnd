package com.den.korolev.football_manager.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserConfig, String> {

    Optional<UserConfig> findByUsername(String username);
    Optional<UserConfig> findByLogin(String login);



    Boolean existsByUsername(String username);
    Boolean existsByLogin(String email);

}
