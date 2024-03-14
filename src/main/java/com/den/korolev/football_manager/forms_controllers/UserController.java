package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import com.den.korolev.football_manager.services.JwtTokenService;
import com.den.korolev.football_manager.services.PasswordService;
import com.den.korolev.football_manager.services.UserUpdateService;
import com.den.korolev.football_manager.user.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserConfigRepository userConfigRepository;

    private final PlayerRepository playerRepository;

    private final JwtTokenService jwtTokenService;

    private final PasswordService passwordService;

    private final HumanRepository humanRepository;

    private final UserUpdateService userUpdateService;

    public UserController(UserConfigRepository userConfigRepository, JwtTokenService jwtTokenService,
                          PasswordService passwordService, UserUpdateService userUpdateService,
                          PlayerRepository playerRepository, HumanRepository humanRepository) {
        this.userConfigRepository = userConfigRepository;
        this.jwtTokenService = jwtTokenService;
        this.passwordService = passwordService;
        this.userUpdateService = userUpdateService;
        this.playerRepository = playerRepository;
        this.humanRepository = humanRepository;
    }

    @PostMapping("/update/config")
    public ResponseEntity<?> updateUserConfig(@RequestAttribute(name = "Uid") Integer UID,
                                              @RequestBody List<UserConfig> configs) {
        userUpdateService.updateUserConfig(configs.getFirst(), configs.get(1), UID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/human")
    public ResponseEntity<?> updateHuman(@RequestAttribute(name = "Uid") Integer UID,
                                              @RequestBody List<Human> humans) {
        userUpdateService.updateHuman(humans.getFirst(), humans.get(1), UID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update/player")
    public ResponseEntity<?> updatePlayer(@RequestAttribute(name = "Uid") Integer UID,
                                         @RequestBody List<Player> players) {
        userUpdateService.updatePlayer(players.getFirst(), players.get(1), UID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/get/config")
    public UserConfig getUserConfig(@RequestAttribute(name = "Uid") Long UID) {
        Optional<UserConfig> configOptional = userConfigRepository.findById(UID);
        if (configOptional.isPresent()) {
            UserConfig userConfig = configOptional.get();
            userConfig.setPassword("********");
            return userConfig;
        }
        throw new RuntimeException();
    }

    @PostMapping("/get/human")
    public Human getHuman(@RequestAttribute(name = "Uid") Integer UID) {
        return humanRepository.getHumanByConfig(UID);
    }

    @PostMapping("/get/player")
    public Player getPlayer(@RequestAttribute(name = "Uid") Long UID) {
        Optional<Player> playerOptional = playerRepository.findById(UID);
        if (playerOptional.isPresent()) {
            return playerOptional.get();
        }
        throw new RuntimeException();
    }

    @PostMapping("/set/config")
    public ResponseEntity<?> setUserConfig(@RequestBody UserConfig userConfig, HttpSession httpSession) {
        userConfig.setPassword(passwordService.makeBCryptHash(userConfig.getPassword()));
        httpSession.setAttribute("config", userConfig);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/register")
    public ResponseEntity<?> getUserPersonalInfo(@RequestBody Human human, HttpSession httpSession) {
        Player player = new Player();
        player.setWeight(0.0);
        player.setHeight(0.0);
        player.setRole("");
        player.setUserConfig((UserConfig) httpSession.getAttribute("config"));
        player.setHuman(human);
        Player savedPlayer = playerRepository.save(player);
        String jwt = jwtTokenService.generateToken(savedPlayer.getId(), savedPlayer.getUserConfig().getRole());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    @PostMapping("/login/{login}")
    public ResponseEntity<?> loginUser(@RequestBody UserLogin userLogin) {

        Optional<UserConfig> userByLogin = userConfigRepository.findByLogin(userLogin.getLogin());
        if (userByLogin.isPresent()) {
            UserConfig user = userByLogin.get();
            if (passwordService.checkIdentity(userLogin.getPassword(), user.getPassword())) {
                String jwt = jwtTokenService.generateToken(user.getId(), user.getRole());
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
            }
        }
        Optional<UserConfig> userByUsername = userConfigRepository.findByUsername(userLogin.getLogin());
        if (userByUsername.isPresent()) {
            UserConfig user = userByUsername.get();
            if (passwordService.checkIdentity(userLogin.getPassword(), user.getPassword())) {
                String jwt = jwtTokenService.generateToken(user.getId(), user.getRole());
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
            }
        }
        return ResponseEntity.ok(new ExceptionResponse("WrongLogin", "Неверный логин или пароль"));
    }

}
