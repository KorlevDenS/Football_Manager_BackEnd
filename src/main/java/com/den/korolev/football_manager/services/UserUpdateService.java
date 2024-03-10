package com.den.korolev.football_manager.services;

import com.den.korolev.football_manager.entities.Human;
import com.den.korolev.football_manager.entities.Player;
import com.den.korolev.football_manager.entities.UserConfig;

public interface UserUpdateService {

    void updateUserConfig(UserConfig orig, UserConfig update, Integer UID);

    void updateHuman(Human orig, Human update, Integer UID);

    void updatePlayer(Player orig, Player update, Integer UID);

}
