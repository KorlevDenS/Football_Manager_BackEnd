package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.Human;
import com.den.korolev.football_manager.entities.Player;

public record Participant(Player player, Human human) {

}
