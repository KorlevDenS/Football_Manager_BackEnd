package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import com.den.korolev.football_manager.services.EventUpdateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event/update")
public class EventUpdateController {

    private final EventUpdateService eventUpdateService;

    public EventUpdateController(EventUpdateService eventUpdateService) {
        this.eventUpdateService = eventUpdateService;
    }

    @PostMapping("/collective/event")
    public ResponseEntity<?> updateCollectiveEvent(@RequestBody List<CollectiveEvent> events) {
        eventUpdateService.updateCollectiveEvent(events.getFirst(), events.get(1));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/match")
    public ResponseEntity<?> updateMatch(@RequestBody List<Match> matches) {
        eventUpdateService.updateMatch(matches.getFirst(), matches.get(1));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/training")
    public ResponseEntity<?> updateTraining(@RequestBody List<Training> trainings) {
        eventUpdateService.updateTraining(trainings.getFirst(), trainings.get(1));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/custom")
    public ResponseEntity<?> updateCustom(@RequestBody List<Custom> customs) {
        eventUpdateService.updateCustom(customs.getFirst(), customs.get(1));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/player/match")
    public ResponseEntity<?> updatePlayerMatch(@RequestBody List<PlayerMatch> playerMatches) {
        eventUpdateService.updatePlayerMatch(playerMatches.getFirst(), playerMatches.get(1));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/player/training")
    public ResponseEntity<?> updatePlayerTraining(@RequestBody List<PlayerTraining> playerTrainings) {
        eventUpdateService.updatePlayerTraining(playerTrainings.getFirst(), playerTrainings.get(1));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/player/custom")
    public ResponseEntity<?> updatePlayerCustom(@RequestBody List<PlayerCustom> playerCustoms) {
        eventUpdateService.updatePlayerCustom(playerCustoms.getFirst(), playerCustoms.get(1));
        return ResponseEntity.ok().build();
    }

}
