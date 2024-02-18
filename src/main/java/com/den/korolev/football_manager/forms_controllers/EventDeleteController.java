package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.PlayerCustomRepository;
import com.den.korolev.football_manager.entities.PlayerMatchRepository;
import com.den.korolev.football_manager.entities.PlayerTrainingRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event/delete")
public class EventDeleteController {

    private final PlayerTrainingRepository playerTrainingRepository;
    private final PlayerMatchRepository playerMatchRepository;
    private final PlayerCustomRepository playerCustomRepository;

    public EventDeleteController(PlayerTrainingRepository playerTrainingRepository,
                                 PlayerMatchRepository playerMatchRepository,
                                 PlayerCustomRepository playerCustomRepository) {
        this.playerTrainingRepository = playerTrainingRepository;
        this.playerMatchRepository = playerMatchRepository;
        this.playerCustomRepository = playerCustomRepository;
    }

    @Transactional
    @DeleteMapping("training")
    public ResponseEntity<?> deleteTraining(@RequestBody Integer training_id, @RequestAttribute(name = "Uid") Integer UID) {
        playerTrainingRepository.deleteTrainingById(training_id, UID);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("match")
    public ResponseEntity<?> deleteMatch(@RequestBody Integer training_id, @RequestAttribute(name = "Uid") Integer UID) {
        playerMatchRepository.deleteMatchById(training_id, UID);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @DeleteMapping("custom")
    public ResponseEntity<?> deleteCustom(@RequestBody Integer training_id, @RequestAttribute(name = "Uid") Integer UID) {
        playerCustomRepository.deleteCustomById(training_id, UID);
        return ResponseEntity.ok().build();
    }
}
