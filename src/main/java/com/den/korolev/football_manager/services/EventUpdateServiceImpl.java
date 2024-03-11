package com.den.korolev.football_manager.services;

import com.den.korolev.football_manager.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EventUpdateServiceImpl implements EventUpdateService{

    private final MatchRepository matchRepository;
    private final TrainingRepository trainingRepository;
    private final CustomRepository customRepository;
    private final PlayerMatchRepository playerMatchRepository;
    private final PlayerTrainingRepository playerTrainingRepository;
    private final PlayerCustomRepository playerCustomRepository;
    private final CollectiveEventRepository collectiveEventRepository;

    public EventUpdateServiceImpl(MatchRepository matchRepository,
                                  TrainingRepository trainingRepository,
                                  CustomRepository customRepository,
                                  PlayerMatchRepository playerMatchRepository,
                                  PlayerTrainingRepository playerTrainingRepository,
                                  PlayerCustomRepository playerCustomRepository,
                                  CollectiveEventRepository collectiveEventRepository) {
        this.matchRepository = matchRepository;
        this.trainingRepository = trainingRepository;
        this.customRepository = customRepository;
        this.playerMatchRepository = playerMatchRepository;
        this.playerTrainingRepository = playerTrainingRepository;
        this.playerCustomRepository = playerCustomRepository;
        this.collectiveEventRepository = collectiveEventRepository;
    }


    @Override
    @Transactional
    public void updateCollectiveEvent(CollectiveEvent orig, CollectiveEvent update) {
        if (!Objects.equals(orig.getLocation(), update.getLocation())) {
            collectiveEventRepository.updateLocation(orig.getId(), update.getLocation());
        }
        if (!Objects.equals(orig.getDate(), update.getDate())) {
            collectiveEventRepository.updateDate(orig.getId(), update.getDate());
        }
        if (!Objects.equals(orig.getTime(), update.getTime())) {
            collectiveEventRepository.updateTime(orig.getId(), update.getTime());
        }
        if (!Objects.equals(orig.getDuration(), update.getDuration())) {
            collectiveEventRepository.updateDuration(orig.getId(), update.getDuration());
        }
        if (!Objects.equals(orig.getDescription(), update.getDescription())) {
            collectiveEventRepository.updateDescription(orig.getId(), update.getDescription());
        }
    }

    @Override
    @Transactional
    public void updateMatch(Match orig, Match update) {
        if (!Objects.equals(orig.getTeam1(), update.getTeam1())) {
            matchRepository.updateTeam1(orig.getId(), update.getTeam1());
        }
        if (!Objects.equals(orig.getTeam2(), update.getTeam2())) {
            matchRepository.updateTeam2(orig.getId(), update.getTeam2());
        }
        if (!Objects.equals(orig.getTeam1_goals(), update.getTeam1_goals())) {
            matchRepository.updateTeam1Goals(orig.getId(), update.getTeam1_goals());
        }
        if (!Objects.equals(orig.getTeam2_goals(), update.getTeam2_goals())) {
            matchRepository.updateTeam2Goals(orig.getId(), update.getTeam2_goals());
        }
        if (!Objects.equals(orig.getField_format(), update.getField_format())) {
            matchRepository.updateFieldFormat(orig.getId(), update.getField_format());
        }
        if (!Objects.equals(orig.getResult(), update.getResult())) {
            matchRepository.updateResult(orig.getId(), update.getResult());
        }
    }

    @Override
    @Transactional
    public void updateTraining(Training orig, Training update) {
        if (!Objects.equals(orig.getType(), update.getType())) {
            trainingRepository.updateType(orig.getId(), update.getType());
        }
        if (!Objects.equals(orig.getField_format(), update.getField_format())) {
            trainingRepository.updateFieldFormat(orig.getId(), update.getField_format());
        }
        if (!Objects.equals(orig.getPlayers_amount(), update.getPlayers_amount())) {
            trainingRepository.updatePlayersAmount(orig.getId(), update.getPlayers_amount());
        }
    }

    @Override
    @Transactional
    public void updateCustom(Custom orig, Custom update) {
        if (!Objects.equals(orig.getName(), update.getName())) {
            customRepository.updateName(orig.getId(), update.getName());
        }
    }

    @Override
    @Transactional
    public void updatePlayerMatch(PlayerMatch orig, PlayerMatch update) {
        if (!Objects.equals(orig.getAssists(), update.getAssists())) {
            playerMatchRepository.updateAssists(orig.getId(), update.getAssists());
        }
        if (!Objects.equals(orig.getField_time(), update.getField_time())) {
            playerMatchRepository.updateFieldTime(orig.getId(), update.getField_time());
        }
        if (!Objects.equals(orig.getRole(), update.getRole())) {
            playerMatchRepository.updateRole(orig.getId(), update.getRole());
        }
        if (!Objects.equals(orig.getGoals(), update.getGoals())) {
            playerMatchRepository.updateGoals(orig.getId(), update.getGoals());
        }
        if (!Objects.equals(orig.getComments(), update.getComments())) {
            playerMatchRepository.updateComments(orig.getId(), update.getComments());
        }
        if (!Objects.equals(orig.getWhat_liked(), update.getWhat_liked())) {
            playerMatchRepository.updateWhatLiked(orig.getId(), update.getWhat_liked());
        }
        if (!Objects.equals(orig.getWhat_disliked(), update.getWhat_disliked())) {
            playerMatchRepository.updateWhatDisliked(orig.getId(), update.getWhat_disliked());
        }
        if (!Objects.equals(orig.getWhat_to_improve(), update.getWhat_to_improve())) {
            playerMatchRepository.updateWhatToImprove(orig.getId(), update.getWhat_to_improve());
        }
    }

    @Override
    @Transactional
    public void updatePlayerTraining(PlayerTraining orig, PlayerTraining update) {
        if (!Objects.equals(orig.getGoals(), update.getGoals())) {
            playerTrainingRepository.updateGoals(orig.getId(), update.getGoals());
        }
        if (!Objects.equals(orig.getComments(), update.getComments())) {
            playerTrainingRepository.updateComments(orig.getId(), update.getComments());
        }
        if (!Objects.equals(orig.getWhat_liked(), update.getWhat_liked())) {
            playerTrainingRepository.updateWhatLiked(orig.getId(), update.getWhat_liked());
        }
        if (!Objects.equals(orig.getWhat_disliked(), update.getWhat_disliked())) {
            playerTrainingRepository.updateWhatDisliked(orig.getId(), update.getWhat_disliked());
        }
        if (!Objects.equals(orig.getWhat_to_improve(), update.getWhat_to_improve())) {
            playerTrainingRepository.updateWhatToImprove(orig.getId(), update.getWhat_to_improve());
        }
    }

    @Override
    @Transactional
    public void updatePlayerCustom(PlayerCustom orig, PlayerCustom update) {
        if (!Objects.equals(orig.getComments(), update.getComments())) {
            playerCustomRepository.updateComments(orig.getId(), update.getComments());
        }
        if (!Objects.equals(orig.getWhat_liked(), update.getWhat_liked())) {
            playerCustomRepository.updateWhatLiked(orig.getId(), update.getWhat_liked());
        }
        if (!Objects.equals(orig.getWhat_disliked(), update.getWhat_disliked())) {
            playerCustomRepository.updateWhatDisliked(orig.getId(), update.getWhat_disliked());
        }
        if (!Objects.equals(orig.getWhat_to_improve(), update.getWhat_to_improve())) {
            playerCustomRepository.updateWhatToImprove(orig.getId(), update.getWhat_to_improve());
        }
    }
}
