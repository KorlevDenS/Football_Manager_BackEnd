package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.UserConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventAddRepository extends JpaRepository<UserConfig, String> {


}
