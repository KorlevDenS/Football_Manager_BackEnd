package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserEventRequest {

    private CollectiveEvent collectiveEvent;
    private Custom custom;
    private PlayerCustom playerCustom;

    private Match match;
    private PlayerMatch playerMatch;

    private Training training;
    private PlayerTraining playerTraining;

    public AddUserEventRequest(CollectiveEvent collectiveEvent, Custom custom, PlayerCustom playerCustom,
                               Match match, PlayerMatch playerMatch,
                               Training training, PlayerTraining playerTraining) {
        this.collectiveEvent = collectiveEvent;
        this.custom = custom;
        this.playerCustom = playerCustom;
        this.match = match;
        this.playerMatch = playerMatch;
        this.training = training;
        this.playerTraining = playerTraining;

    }

}
