package com.den.korolev.football_manager.request_params;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExercisesMatchRequest {

    private Long eventId;
    private Long[] exercisesIds;

    public ExercisesMatchRequest(Long eventId, Long[] exercisesIds) {
        this.eventId = eventId;
        this.exercisesIds = exercisesIds;
    }
}
