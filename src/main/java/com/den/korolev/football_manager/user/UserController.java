package com.den.korolev.football_manager.user;

import com.den.korolev.football_manager.entities.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    private final PlayerRepository playerRepository;

    private final JwtTokenProvider jwtTokenProvider;

    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
                          PasswordEncoder passwordEncoder, PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.playerRepository = playerRepository;
    }


    @PostMapping("/register/{username}")
    public ResponseEntity<?> registerUser(@RequestBody UserConfig userConfig) {
        userConfig.setPassword(passwordEncoder.makeBCryptHash(userConfig.getPassword()));
        Player savedPlayer;
        try {
            Player player = new Player();
            Human human = new Human();
            player.setUserConfig(userConfig);
            player.setHuman(human);
            savedPlayer = playerRepository.save(player);

        } catch (Exception e) {
            return ResponseEntity.ok(new ExceptionResponse(e.getClass().getName(), e.getMessage()));
        }
        String jwt = jwtTokenProvider.generateToken(savedPlayer.getId(), savedPlayer.getUserConfig().getRole());
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    @PostMapping("/login/{login}")
    public ResponseEntity<?> loginUser(@RequestBody UserLogin userLogin) {

        Optional<UserConfig> userByLogin = userRepository.findByLogin(userLogin.getLogin());
        if (userByLogin.isPresent()) {
            UserConfig user = userByLogin.get();
            if (passwordEncoder.checkIdentity(userLogin.getPassword(), user.getPassword())) {
                String jwt = jwtTokenProvider.generateToken(user.getId(), user.getRole());
                return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
            }
        }
        Optional<UserConfig> userByUsername = userRepository.findByUsername(userLogin.getLogin());
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
