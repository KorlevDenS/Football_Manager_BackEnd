package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import com.den.korolev.football_manager.request_params.ClubCollectiveEvent;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

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
    private final ClubEventRepository clubEventRepository;


    public EventGetController(PlayerMatchRepository playerMatchRepository,
                              PlayerTrainingRepository playerTrainingRepository,
                              PlayerCustomRepository playerCustomRepository,
                              MatchRepository matchRepository,
                              TrainingRepository trainingRepository,
                              CustomRepository customRepository,
                              CollectiveEventRepository collectiveEventRepository,
                              ClubEventRepository clubEventRepository) {
        this.playerMatchRepository = playerMatchRepository;
        this.playerTrainingRepository = playerTrainingRepository;
        this.playerCustomRepository = playerCustomRepository;
        this.matchRepository = matchRepository;
        this.trainingRepository = trainingRepository;
        this.customRepository = customRepository;
        this.collectiveEventRepository = collectiveEventRepository;
        this.clubEventRepository = clubEventRepository;
    }

    @GetMapping("collective/events")
    public List<CollectiveEvent> getEvents(@RequestAttribute(name = "Uid") Long UID) {
        return collectiveEventRepository.findAllByUserID(UID);
    }

    @GetMapping("club/collective/events/{clubId}")
    public List<ClubCollectiveEvent> getClubEvents(@PathVariable Integer clubId,
                                                   @RequestAttribute(name = "Uid") Integer UID) {
        List<ClubEvent> clubEvents = clubEventRepository.getClubEventsByClubAndAdmin(clubId, UID);
        return clubEvents.stream()
                .map(clubEvent -> new ClubCollectiveEvent(clubEvent, clubEvent.getId_collective_event()))
                .toList();
    }

    @GetMapping("collective/events/{begin}/{end}")
    public List<CollectiveEvent> getEventsByTimePeriod(@PathVariable String begin, @PathVariable String end,
                                                       @RequestAttribute(name = "Uid") Long UID) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return collectiveEventRepository.findAllByTimePeriod(UID, dateFormat.parse(begin), dateFormat.parse(end));
    }

    @GetMapping("club/collective/events/{clubId}/{begin}/{end}")
    public List<ClubCollectiveEvent> getClubEventsByTimePeriod(@PathVariable String begin, @PathVariable String end,
                                                               @PathVariable Integer clubId,
                                                               @RequestAttribute(name = "Uid") Integer UID) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<ClubEvent> clubEvents = clubEventRepository.getClubEventsByTimePeriod(dateFormat.parse(begin),
                dateFormat.parse(end), clubId, UID);
        return clubEvents.stream()
                .map(clubEvent -> new ClubCollectiveEvent(clubEvent, clubEvent.getId_collective_event()))
                .collect(Collectors.toList());
    }

    @PostMapping("match")
    public Match getMatchById(@RequestBody Integer id, @RequestAttribute(name = "Uid") Long UID) {
        return matchRepository.findByUserAndEvent(id, UID);
    }

    @PostMapping("club/match")
    public Match getClubMatchById(@RequestBody List<Integer> list, @RequestAttribute(name = "Uid") Long UID) {
        return matchRepository.findByClubAndEvent(list.getFirst(), list.get(1), UID);
    }

    @PostMapping("training")
    public Training getTrainingById(@RequestBody Integer id, @RequestAttribute(name = "Uid") Long UID) {
        return trainingRepository.findByUserAndEvent(id, UID);
    }

    @PostMapping("club/training")
    public Training getClubTrainingById(@RequestBody List<Integer> list, @RequestAttribute(name = "Uid") Long UID) {
        return trainingRepository.findByClubAndEvent(list.getFirst(), list.get(1), UID);
    }

    @PostMapping("custom")
    public Custom getCustomById(@RequestBody Integer id, @RequestAttribute(name = "Uid") Long UID) {
        return customRepository.findByUserAndEvent(id, UID);
    }

    @PostMapping("club/custom")
    public Custom getClubCustomById(@RequestBody List<Integer> list, @RequestAttribute(name = "Uid") Long UID) {
        return customRepository.findByClubAndEvent(list.getFirst(), list.get(1), UID);
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
