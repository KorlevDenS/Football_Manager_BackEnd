package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import com.den.korolev.football_manager.user.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserConfigRepository userConfigRepository;

    private final PlayerRepository playerRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserConfigRepository userConfigRepository, JwtTokenProvider jwtTokenProvider,
                          PasswordEncoder passwordEncoder, PlayerRepository playerRepository) {
        this.userConfigRepository = userConfigRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.playerRepository = playerRepository;
    }


    @PostMapping("/set/config")
    public ResponseEntity<?> getUserConfig(@RequestBody UserConfig userConfig, HttpSession httpSession) {
        userConfig.setPassword(passwordEncoder.makeBCryptHash(userConfig.getPassword()));
        httpSession.setAttribute("config", userConfig);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/register")
    public ResponseEntity<?> getUserPersonalInfo(@RequestBody Human human, HttpSession httpSession) {
        Player player = new Player();
        player.setUserConfig((UserConfig) httpSession.getAttribute("config"));
        player.setHuman(human);
        Player savedPlayer = playerRepository.save(player);
        String jwt = jwtTokenProvider.generateToken(savedPlayer.getId(), savedPlayer.getUserConfig().getRole());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    @PostMapping("/login/{login}")
    public ResponseEntity<?> loginUser(@RequestBody UserLogin userLogin) {

        Optional<UserConfig> userByLogin = userConfigRepository.findByLogin(userLogin.getLogin());
        if (userByLogin.isPresent()) {
            UserConfig user = userByLogin.get();
            if (passwordEncoder.checkIdentity(userLogin.getPassword(), user.getPassword())) {
                String jwt = jwtTokenProvider.generateToken(user.getId(), user.getRole());
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
            }
        }
        Optional<UserConfig> userByUsername = userConfigRepository.findByUsername(userLogin.getLogin());
        if (userByUsername.isPresent()) {
            UserConfig user = userByUsername.get();
            if (passwordEncoder.checkIdentity(userLogin.getPassword(), user.getPassword())) {
                String jwt = jwtTokenProvider.generateToken(user.getId(), user.getRole());
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
            }
        }
        return ResponseEntity.ok(new ExceptionResponse("WrongLogin", "Неверный логин или пароль"));
    }

}
