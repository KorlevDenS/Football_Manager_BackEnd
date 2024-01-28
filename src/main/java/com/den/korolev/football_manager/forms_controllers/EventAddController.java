package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import com.den.korolev.football_manager.request_params.AddUserEventRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/new/event")
public class EventAddController {

    private final PlayerTrainingRepository playerTrainingRepository;
    private final PlayerCustomRepository playerCustomRepository;

    private final CustomRepository customRepository;
    private final PlayerMatchRepository playerMatchRepository;

    private final MatchRepository matchRepository;

    private final TrainingRepository trainingRepository;

    private final PlayerRepository playerRepository;

    private final CollectiveEventRepository collectiveEventRepository;

    public EventAddController(PlayerTrainingRepository playerTrainingRepository,
                              PlayerMatchRepository playerMatchRepository,
                              PlayerCustomRepository playerCustomRepository,
                              PlayerRepository playerRepository,
                              TrainingRepository trainingRepository,
                              MatchRepository matchRepository,
                              CustomRepository customRepository,
                              CollectiveEventRepository collectiveEventRepository) {
        this.playerTrainingRepository = playerTrainingRepository;
        this.playerMatchRepository = playerMatchRepository;
        this.playerCustomRepository = playerCustomRepository;
        this.playerRepository = playerRepository;
        this.trainingRepository = trainingRepository;
        this.matchRepository = matchRepository;
        this.customRepository = customRepository;
        this.collectiveEventRepository = collectiveEventRepository;
    }


    @GetMapping("get")
    public List<CollectiveEvent> getEvents(@RequestAttribute(name = "Uid") Long UID) {
        return collectiveEventRepository.findAllByUserID(UID);
    }

    @GetMapping("get/{begin}/{end}")
    public List<CollectiveEvent> getEventsByTimePeriod(@PathVariable String begin, @PathVariable String end, @RequestAttribute(name = "Uid") Long UID) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        return collectiveEventRepository.findAllByTimePeriod(UID, dateFormat.parse(begin), dateFormat.parse(end));
    }


    @PostMapping("add")
    public ResponseEntity<?> addUserEvent(@RequestBody AddUserEventRequest addUserEventRequest,
                                          @RequestAttribute(name = "Uid") Long UID) {

        Optional<Player> optionalPlayer = playerRepository.findById(UID);
        if (optionalPlayer.isEmpty()) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка сервера");
        Player player = optionalPlayer.get();

        if (addUserEventRequest.getTraining() != null) {

            PlayerTraining playerTraining = addUserEventRequest.getPlayerTraining();
            playerTraining.setPlayer(player);

            Training training = addUserEventRequest.getTraining();

            CollectiveEvent collectiveEvent = addUserEventRequest.getCollectiveEvent();
            training.setCollectiveEvent(collectiveEvent);
            trainingRepository.save(training);

            playerTraining.setTraining(training);

            playerTrainingRepository.save(playerTraining);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Saved\"}");

        } else if (addUserEventRequest.getMatch() != null) {

            PlayerMatch playerMatch = addUserEventRequest.getPlayerMatch();
            playerMatch.setPlayer(player);

            Match match = addUserEventRequest.getMatch();

            CollectiveEvent collectiveEvent = addUserEventRequest.getCollectiveEvent();
            match.setCollectiveEvent(collectiveEvent);
            matchRepository.save(match);

            playerMatch.setMatch(match);

            playerMatchRepository.save(playerMatch);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Saved\"}");

        } else if (addUserEventRequest.getCustom() != null) {

            PlayerCustom playerCustom = addUserEventRequest.getPlayerCustom();
            playerCustom.setPlayer(player);

            Custom custom = addUserEventRequest.getCustom();

            CollectiveEvent collectiveEvent = addUserEventRequest.getCollectiveEvent();
            custom.setCollectiveEvent(collectiveEvent);
            customRepository.save(custom);

            playerCustom.setCustom(custom);

            playerCustomRepository.save(playerCustom);
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Saved\"}");

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Событие не существует");
        }
    }






}
