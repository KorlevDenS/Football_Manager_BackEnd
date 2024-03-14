package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.CollectiveEvent;
import com.den.korolev.football_manager.entities.Custom;
import com.den.korolev.football_manager.entities.PlayerCustom;

public record CustomAddRequest(CollectiveEvent collectiveEvent, Custom custom, PlayerCustom playerCustom) {

}
