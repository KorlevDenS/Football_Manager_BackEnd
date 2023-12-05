package com.den.korolev.football_manager;

import com.den.korolev.football_manager.user.InvalidTokenException;
import com.den.korolev.football_manager.user.JwtTokenProvider;
import com.den.korolev.football_manager.user.PasswordEncoder;

public class MainTest {


    public static void main(String[] args) {
        JwtTokenProvider tokenProvider = new JwtTokenProvider();

        String token = tokenProvider.generateToken(12L, "user");

        System.out.println(token);

        try {
            System.out.println(tokenProvider.verifyToken(token));
        } catch (InvalidTokenException e) {
            System.out.println(e.getMessage());
        }

    }
}
