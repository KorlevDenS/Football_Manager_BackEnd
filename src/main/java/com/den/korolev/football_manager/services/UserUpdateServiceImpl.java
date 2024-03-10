package com.den.korolev.football_manager.services;

import com.den.korolev.football_manager.entities.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserUpdateServiceImpl implements UserUpdateService {

    private final UserConfigRepository userConfigRepository;

    private final PasswordService passwordService;
    private final HumanRepository humanRepository;
    private final PlayerRepository playerRepository;

    public UserUpdateServiceImpl(UserConfigRepository userConfigRepository, PasswordService passwordService,
                                 HumanRepository humanRepository,
                                 PlayerRepository playerRepository) {
        this.userConfigRepository = userConfigRepository;
        this.passwordService = passwordService;
        this.humanRepository = humanRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional
    public void updateUserConfig(UserConfig orig, UserConfig update, Integer UID) {
        if (!Objects.equals(orig.getLogin(), update.getLogin())) {
            userConfigRepository.updateLogin(UID, update.getLogin());
        }
        if (!Objects.equals(orig.getPassword(), update.getPassword())) {
            Optional<UserConfig> userConfigOptional = userConfigRepository.findById(Long.valueOf(UID));
            UserConfig userConfig;
            if (userConfigOptional.isPresent()) {
                userConfig = userConfigOptional.get();
            } else throw new RuntimeException();

            if (!(passwordService.checkIdentity(update.getPassword(), userConfig.getPassword()))) {
                userConfigRepository.updatePassword(UID, passwordService.makeBCryptHash(update.getPassword()));
            }
        }
        if (!Objects.equals(orig.getUsername(), update.getUsername())) {
            userConfigRepository.updateUsername(UID, update.getUsername());
        }
    }

    @Override
    @Transactional
    public void updateHuman(Human orig, Human update, Integer UID) {
        if (!Objects.equals(orig.getName(), update.getName())) {
            humanRepository.updateName(UID, update.getName());
        }
        if (!Objects.equals(orig.getSurname(), update.getSurname())) {
            humanRepository.updateSurname(UID, update.getSurname());
        }
        if (!Objects.equals(orig.getPatronymic(), update.getPatronymic())) {
            humanRepository.updatePatronymic(UID, update.getPatronymic());
        }
        if (!Objects.equals(orig.getBirthday(), update.getBirthday())) {
            humanRepository.updateBirthday(UID, update.getBirthday());
        }
        if (!Objects.equals(orig.getSex(), update.getSex())) {
            humanRepository.updateSex(UID, update.getSex());
        }
        if (!Objects.equals(orig.getPassport_id(), update.getPassport_id())) {
            humanRepository.updatePassportId(UID, update.getPassport_id());
        }
    }

    @Override
    @Transactional
    public void updatePlayer(Player orig, Player update, Integer UID) {
        if (!Objects.equals(orig.getRole(), update.getRole())) {
            playerRepository.updateRole(UID, update.getRole());
        }
        if (!Objects.equals(orig.getHeight(), update.getHeight())) {
            playerRepository.updateHeight(UID, update.getHeight());
        }
        if (!Objects.equals(orig.getWeight(), update.getWeight())) {
            playerRepository.updateWeight(UID, update.getWeight());
        }
    }
}
