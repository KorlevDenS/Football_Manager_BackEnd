package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingAddRequest {

    private CollectiveEvent collectiveEvent;
    private Training training;
    private PlayerTraining playerTraining;

    public TrainingAddRequest(CollectiveEvent collectiveEvent, Training training, PlayerTraining playerTraining) {
        this.collectiveEvent = collectiveEvent;
        this.training = training;
        this.playerTraining = playerTraining;
    }
}
