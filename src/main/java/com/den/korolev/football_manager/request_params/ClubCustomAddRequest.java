package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.ClubEvent;
import com.den.korolev.football_manager.entities.CollectiveEvent;
import com.den.korolev.football_manager.entities.Custom;

import java.util.List;

public record ClubCustomAddRequest(Integer clubId, ClubEvent clubEvent, CollectiveEvent collectiveEvent,
                                   Custom custom, List<Integer> players) {
}
