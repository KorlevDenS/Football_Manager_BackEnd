package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import jakarta.transaction.Transactional;
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
    public List<CollectiveEvent> getEventsByTimePeriod(@PathVariable String begin, @PathVariable String end,
                                                       @RequestAttribute(name = "Uid") Long UID) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return collectiveEventRepository.findAllByTimePeriod(UID, dateFormat.parse(begin), dateFormat.parse(end));
    }

    @PostMapping("match")
    public Match getMatchById(@RequestBody Integer id, @RequestAttribute(name = "Uid") Long UID) {
        return matchRepository.findByUserAndEvent(id, UID);
    }

    @PostMapping("training")
    public Training getTrainingById(@RequestBody Integer id, @RequestAttribute(name = "Uid") Long UID) {
        return trainingRepository.findByUserAndEvent(id, UID);
    }

    @PostMapping("custom")
    public Custom getCustomById(@RequestBody Integer id, @RequestAttribute(name = "Uid") Long UID) {
        return customRepository.findByUserAndEvent(id, UID);
    }



    @Transactional
    @PostMapping("player/match")
    public PlayerMatch getPlayerMatchById(@RequestBody Integer id, @RequestAttribute(name = "Uid") Integer UID) {
        return playerMatchRepository.find_player_match_by_id(id, UID);
    }

    @Transactional
    @PostMapping("player/training")
    public PlayerTraining getPlayerTrainingById(@RequestBody Integer id, @RequestAttribute(name = "Uid") Integer UID) {
        return playerTrainingRepository.find_player_training_by_id(id, UID);
    }

    @Transactional
    @PostMapping("player/custom")
    public PlayerCustom getPlayerCustomById(@RequestBody Integer id, @RequestAttribute(name = "Uid") Integer UID) {
        return playerCustomRepository.find_player_custom_by_id(id, UID);
    }

}
