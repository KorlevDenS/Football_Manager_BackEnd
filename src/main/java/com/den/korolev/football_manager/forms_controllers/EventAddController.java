package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import com.den.korolev.football_manager.request_params.*;
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
    private final ClubRepository clubRepository;
    private final ClubEventRepository clubEventRepository;
    private final CollectiveEventRepository collectiveEventRepository;

    public EventAddController(PlayerTrainingRepository playerTrainingRepository,
                              PlayerMatchRepository playerMatchRepository,
                              PlayerCustomRepository playerCustomRepository,
                              PlayerRepository playerRepository,
                              TrainingRepository trainingRepository,
                              MatchRepository matchRepository,
                              CustomRepository customRepository,
                              ClubRepository clubRepository,
                              ClubEventRepository clubEventRepository,
                              CollectiveEventRepository collectiveEventRepository) {
        this.playerTrainingRepository = playerTrainingRepository;
        this.playerMatchRepository = playerMatchRepository;
        this.playerCustomRepository = playerCustomRepository;
        this.playerRepository = playerRepository;
        this.trainingRepository = trainingRepository;
        this.matchRepository = matchRepository;
        this.customRepository = customRepository;
        this.clubRepository = clubRepository;
        this.clubEventRepository = clubEventRepository;
        this.collectiveEventRepository = collectiveEventRepository;
    }

    @PostMapping("new/training")
    public Training addTraining(@RequestBody TrainingAddRequest trainingAddRequest,
                                @RequestAttribute(name = "Uid") Long UID) {
        Optional<Player> optionalPlayer = playerRepository.findById(UID);
        if (optionalPlayer.isEmpty()) throw new RuntimeException();
        Player player = optionalPlayer.get();
        PlayerTraining playerTraining = trainingAddRequest.playerTraining();
        playerTraining.setPlayer(player);
        Training training = trainingAddRequest.training();
        CollectiveEvent collectiveEvent = trainingAddRequest.collectiveEvent();
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
        PlayerMatch playerMatch = matchAddRequest.playerMatch();
        playerMatch.setPlayer(player);
        Match match = matchAddRequest.match();
        CollectiveEvent collectiveEvent = matchAddRequest.collectiveEvent();
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
        PlayerCustom playerCustom = customAddRequest.playerCustom();
        playerCustom.setPlayer(player);
        Custom custom = customAddRequest.custom();
        CollectiveEvent collectiveEvent = customAddRequest.collectiveEvent();
        custom.setCollectiveEvent(collectiveEvent);
        customRepository.save(custom);
        playerCustom.setCustom(custom);
        playerCustomRepository.save(playerCustom);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Saved\"}");
    }

    @PostMapping("new/club/custom")
    public ResponseEntity<?> addClubCustom(@RequestBody ClubCustomAddRequest customAddRequest,
                                           @RequestAttribute(name = "Uid") Integer UID) {

        Club club = clubRepository.findByIdAndAdmin(customAddRequest.clubId(), UID);
        CollectiveEvent collectiveEvent = customAddRequest.collectiveEvent();
        CollectiveEvent savedCollectiveEvent = collectiveEventRepository.save(collectiveEvent);

        ClubEvent clubEvent = customAddRequest.clubEvent();
        clubEvent.setId_club(club);
        clubEvent.setId_collective_event(savedCollectiveEvent);

        Custom custom = customAddRequest.custom();
        custom.setCollectiveEvent(savedCollectiveEvent);
        clubEventRepository.save(clubEvent);
        customRepository.save(custom);

        for (Integer playerId : customAddRequest.players()) {
            Optional<Player> optionalPlayer = playerRepository.findById(Long.valueOf(playerId));
            if (optionalPlayer.isEmpty()) continue;
            Player player = optionalPlayer.get();
            PlayerCustom playerCustom = new PlayerCustom();
            playerCustom.setPlayer(player);
            playerCustom.setCustom(custom);
            playerCustomRepository.save(playerCustom);
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("new/club/training")
    public Training addClubTraining(@RequestBody ClubTrainingAddRequest trainingAddRequest,
                                           @RequestAttribute(name = "Uid") Integer UID) {
        Club club = clubRepository.findByIdAndAdmin(trainingAddRequest.clubId(), UID);
        CollectiveEvent collectiveEvent = trainingAddRequest.collectiveEvent();
        CollectiveEvent savedCollectiveEvent = collectiveEventRepository.save(collectiveEvent);

        ClubEvent clubEvent = trainingAddRequest.clubEvent();
        clubEvent.setId_club(club);
        clubEvent.setId_collective_event(savedCollectiveEvent);

        Training training = trainingAddRequest.training();
        training.setCollectiveEvent(savedCollectiveEvent);

        clubEventRepository.save(clubEvent);
        Training savedTraining = trainingRepository.save(training);

        for (Integer playerId : trainingAddRequest.players()) {
            Optional<Player> optionalPlayer = playerRepository.findById(Long.valueOf(playerId));
            if (optionalPlayer.isEmpty()) continue;
            Player player = optionalPlayer.get();
            PlayerTraining playerTraining = new PlayerTraining();
            playerTraining.setPlayer(player);
            playerTraining.setTraining(training);
            playerTrainingRepository.save(playerTraining);
        }
        return training;
    }

    @PostMapping("new/club/match")
    public ResponseEntity<?> addClubMatch(@RequestBody ClubMatchAddRequest matchAddRequest,
                                             @RequestAttribute(name = "Uid") Integer UID) {
        Club club = clubRepository.findByIdAndAdmin(matchAddRequest.clubId(), UID);
        CollectiveEvent collectiveEvent = matchAddRequest.collectiveEvent();
        CollectiveEvent savedCollectiveEvent = collectiveEventRepository.save(collectiveEvent);

        ClubEvent clubEvent = matchAddRequest.clubEvent();
        clubEvent.setId_club(club);
        clubEvent.setId_collective_event(savedCollectiveEvent);

        Match match = matchAddRequest.match();
        match.setCollectiveEvent(savedCollectiveEvent);

        clubEventRepository.save(clubEvent);
        matchRepository.save(match);

        for (Integer playerId : matchAddRequest.players()) {
            Optional<Player> optionalPlayer = playerRepository.findById(Long.valueOf(playerId));
            if (optionalPlayer.isEmpty()) continue;
            Player player = optionalPlayer.get();
            PlayerMatch playerMatch = new PlayerMatch();
            playerMatch.setPlayer(player);
            playerMatch.setMatch(match);
            playerMatchRepository.save(playerMatch);
        }
        return ResponseEntity.ok().build();
    }

}
