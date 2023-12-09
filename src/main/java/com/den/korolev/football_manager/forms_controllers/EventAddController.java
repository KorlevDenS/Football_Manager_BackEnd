package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import com.den.korolev.football_manager.request_params.AddUserEventRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/new/event")
public class EventAddController {

    private final PlayerTrainingRepository playerTrainingRepository;
    private final PlayerCustomRepository playerCustomRepository;
    private final PlayerMatchRepository playerMatchRepository;

    private final TrainingRepository trainingRepository;

    private final PlayerRepository playerRepository;

    public EventAddController(PlayerTrainingRepository playerTrainingRepository,
                              PlayerMatchRepository playerMatchRepository,
                              PlayerCustomRepository playerCustomRepository,
                              PlayerRepository playerRepository,
                              TrainingRepository trainingRepository) {
        this.playerTrainingRepository = playerTrainingRepository;
        this.playerMatchRepository = playerMatchRepository;
        this.playerCustomRepository = playerCustomRepository;
        this.playerRepository = playerRepository;
        this.trainingRepository = trainingRepository;
    }

    @PostMapping("add")
    public ResponseEntity<?> addUserEvent(@RequestBody AddUserEventRequest addUserEventRequest,
                                          @RequestAttribute(name = "Uid") Long UID) {

        if (addUserEventRequest.getTraining() != null) {

            Optional<Player> optionalPlayer = playerRepository.findById(UID);
            if (optionalPlayer.isEmpty()) return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка сервера");
            Player player = optionalPlayer.get();

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

        } else if (addUserEventRequest.getCustom() != null) {

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Событие не существует");
        }
        return ResponseEntity.ok("GOT YOUR CLASS");
    }


}
