package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.*;

public record TrainingAddRequest(CollectiveEvent collectiveEvent, Training training, PlayerTraining playerTraining) {

}
