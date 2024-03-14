package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.ClubEvent;
import com.den.korolev.football_manager.entities.CollectiveEvent;
import com.den.korolev.football_manager.entities.Training;

import java.util.List;

public record ClubTrainingAddRequest(Integer clubId, ClubEvent clubEvent, CollectiveEvent collectiveEvent,
                                     Training training, List<Integer> players) {
}
