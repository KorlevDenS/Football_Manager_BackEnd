package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.CollectiveEvent;
import com.den.korolev.football_manager.entities.Custom;
import com.den.korolev.football_manager.entities.PlayerCustom;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomAddRequest {

    private CollectiveEvent collectiveEvent;
    private Custom custom;
    private PlayerCustom playerCustom;

    public CustomAddRequest(CollectiveEvent collectiveEvent, Custom custom, PlayerCustom playerCustom) {
        this.collectiveEvent = collectiveEvent;
        this.custom = custom;
        this.playerCustom = playerCustom;
    }

}
