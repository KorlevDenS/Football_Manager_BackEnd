package com.den.korolev.football_manager;

import org.springframework.data.jpa.repository.JpaRepository;

@Deprecated
public interface ClientRepository extends JpaRepository<Client, Long> {

    //@Query()
    Client findByEmail(String email);
}
