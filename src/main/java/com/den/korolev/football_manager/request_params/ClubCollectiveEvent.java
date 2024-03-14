package com.den.korolev.football_manager.request_params;

import com.den.korolev.football_manager.entities.ClubEvent;
import com.den.korolev.football_manager.entities.CollectiveEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class ClubCollectiveEvent {
    private final ClubEvent clubEvent;
    private final CollectiveEvent collectiveEvent;

    public ClubCollectiveEvent(ClubEvent clubEvent, CollectiveEvent collectiveEvent) {
        this.clubEvent = clubEvent;
        this.collectiveEvent = collectiveEvent;
    }

}
