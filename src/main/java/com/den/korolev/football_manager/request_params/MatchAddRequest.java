package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.CollectiveEvent;
import com.den.korolev.football_manager.entities.Match;
import com.den.korolev.football_manager.entities.PlayerMatch;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchAddRequest {

    private CollectiveEvent collectiveEvent;
    private Match match;
    private PlayerMatch playerMatch;

    public MatchAddRequest(CollectiveEvent collectiveEvent, Match match, PlayerMatch playerMatch) {
        this.collectiveEvent = collectiveEvent;
        this.match = match;
        this.playerMatch = playerMatch;
    }
}
