package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.ClubEvent;
import com.den.korolev.football_manager.entities.CollectiveEvent;
import com.den.korolev.football_manager.entities.Match;

import java.util.List;

public record ClubMatchAddRequest(Integer clubId, ClubEvent clubEvent, CollectiveEvent collectiveEvent,
                                  Match match, List<Integer> players) {

}
