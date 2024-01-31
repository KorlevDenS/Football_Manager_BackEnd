package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequestMapping("/event/get")
public class EventGetController {

    private final MatchRepository matchRepository;
    private final TrainingRepository trainingRepository;
    private final CustomRepository customRepository;
    private final PlayerMatchRepository playerMatchRepository;
    private final PlayerTrainingRepository playerTrainingRepository;
    private final PlayerCustomRepository playerCustomRepository;
    private final CollectiveEventRepository collectiveEventRepository;


    public EventGetController(PlayerMatchRepository playerMatchRepository,
                              PlayerTrainingRepository playerTrainingRepository,
                              PlayerCustomRepository playerCustomRepository,
                              MatchRepository matchRepository,
                              TrainingRepository trainingRepository,
                              CustomRepository customRepository,
                              CollectiveEventRepository collectiveEventRepository) {
        this.playerMatchRepository = playerMatchRepository;
        this.playerTrainingRepository = playerTrainingRepository;
        this.playerCustomRepository = playerCustomRepository;
        this.matchRepository = matchRepository;
        this.trainingRepository = trainingRepository;
        this.customRepository = customRepository;
        this.collectiveEventRepository = collectiveEventRepository;
    }

    @GetMapping("collective/events")
    public List<CollectiveEvent> getEvents(@RequestAttribute(name = "Uid") Long UID) {
        return collectiveEventRepository.findAllByUserID(UID);
    }

    @GetMapping("collective/events/{begin}/{end}")
    public List<CollectiveEvent> getEventsByTimePeriod(@PathVariable String begin, @PathVariable String end, @RequestAttribute(name = "Uid") Long UID) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return collectiveEventRepository.findAllByTimePeriod(UID, dateFormat.parse(begin), dateFormat.parse(end));
    }

    @PostMapping("collective/events/")
    public Match getMatchById(@RequestBody Long id, @RequestAttribute(name = "Uid") Long UID) {
        return matchRepository.findByUserAndEvent(id, UID);
    }

}
