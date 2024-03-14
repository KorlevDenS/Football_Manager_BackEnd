package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.CollectiveEvent;
import com.den.korolev.football_manager.entities.Match;
import com.den.korolev.football_manager.entities.PlayerMatch;

public record MatchAddRequest(CollectiveEvent collectiveEvent, Match match, PlayerMatch playerMatch) {

}
