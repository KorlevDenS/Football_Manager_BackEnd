package com.den.korolev.football_manager.services;

import com.den.korolev.football_manager.entities.*;

public interface EventUpdateService {

    void updateCollectiveEvent(CollectiveEvent orig, CollectiveEvent update);

    void updateMatch(Match orig, Match update);

    void updateTraining(Training orig, Training update);

    void updateCustom(Custom orig, Custom update);

    void updatePlayerMatch(PlayerMatch orig, PlayerMatch update);

    void updatePlayerTraining(PlayerTraining orig, PlayerTraining update);

    void updatePlayerCustom(PlayerCustom orig, PlayerCustom update);
}
