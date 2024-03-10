package com.den.korolev.football_manager.services;

public interface PasswordService {

    String makeBCryptHash(String password);

    boolean checkIdentity(String rawPassword, String encodedPassword);

}
