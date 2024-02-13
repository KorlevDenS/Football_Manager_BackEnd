package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import com.den.korolev.football_manager.request_params.CustomAddRequest;
import com.den.korolev.football_manager.request_params.MatchAddRequest;
import com.den.korolev.football_manager.request_params.TrainingAddRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/event/add")
public class EventAddController {

    private final PlayerTrainingRepository playerTrainingRepository;
    private final PlayerCustomRepository playerCustomRepository;

    private final CustomRepository customRepository;
    private final PlayerMatchRepository playerMatchRepository;

    private final MatchRepository matchRepository;

    private final TrainingRepository trainingRepository;

    private final PlayerRepository playerRepository;

    public EventAddController(PlayerTrainingRepository playerTrainingRepository,
                              PlayerMatchRepository playerMatchRepository,
                              PlayerCustomRepository playerCustomRepository,
                              PlayerRepository playerRepository,
                              TrainingRepository trainingRepository,
                              MatchRepository matchRepository,
                              CustomRepository customRepository) {
        this.playerTrainingRepository = playerTrainingRepository;
        this.playerMatchRepository = playerMatchRepository;
        this.playerCustomRepository = playerCustomRepository;
        this.playerRepository = playerRepository;
        this.trainingRepository = trainingRepository;
        this.matchRepository = matchRepository;
        this.customRepository = customRepository;
    }

    @PostMapping("new/training")
    public Training addTraining(@RequestBody TrainingAddRequest trainingAddRequest,
                                @RequestAttribute(name = "Uid") Long UID) {
        Optional<Player> optionalPlayer = playerRepository.findById(UID);
        if (optionalPlayer.isEmpty()) throw new RuntimeException();
        Player player = optionalPlayer.get();
        PlayerTraining playerTraining = trainingAddRequest.getPlayerTraining();
        playerTraining.setPlayer(player);
        Training training = trainingAddRequest.getTraining();
        CollectiveEvent collectiveEvent = trainingAddRequest.getCollectiveEvent();
        training.setCollectiveEvent(collectiveEvent);
        trainingRepository.save(training);
        playerTraining.setTraining(training);
        playerTrainingRepository.save(playerTraining);
        return training;
    }

    @PostMapping("new/match")
    public ResponseEntity<?> addMatch(@RequestBody MatchAddRequest matchAddRequest,
                                      @RequestAttribute(name = "Uid") Long UID) {
        Optional<Player> optionalPlayer = playerRepository.findById(UID);
        if (optionalPlayer.isEmpty()) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка сервера");
        Player player = optionalPlayer.get();
        PlayerMatch playerMatch = matchAddRequest.getPlayerMatch();
        playerMatch.setPlayer(player);
        Match match = matchAddRequest.getMatch();
        CollectiveEvent collectiveEvent = matchAddRequest.getCollectiveEvent();
        match.setCollectiveEvent(collectiveEvent);
        matchRepository.save(match);
        playerMatch.setMatch(match);
        playerMatchRepository.save(playerMatch);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Saved\"}");
    }

    @PostMapping("new/custom")
    public ResponseEntity<?> addCustom(@RequestBody CustomAddRequest customAddRequest,
                                       @RequestAttribute(name = "Uid") Long UID) {
        Optional<Player> optionalPlayer = playerRepository.findById(UID);
        if (optionalPlayer.isEmpty()) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка сервера");
        Player player = optionalPlayer.get();
        PlayerCustom playerCustom = customAddRequest.getPlayerCustom();
        playerCustom.setPlayer(player);
        Custom custom = customAddRequest.getCustom();
        CollectiveEvent collectiveEvent = customAddRequest.getCollectiveEvent();
        custom.setCollectiveEvent(collectiveEvent);
        customRepository.save(custom);
        playerCustom.setCustom(custom);
        playerCustomRepository.save(playerCustom);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Saved\"}");
    }

}
