package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/club")
public class ClubController {

    private final ClubRepository clubRepository;
    private final HumanRepository humanRepository;
    private final UserConfigRepository userConfigRepository;
    private final ClubManagementRepository clubManagementRepository;

    public ClubController(ClubRepository clubRepository,
                          HumanRepository humanRepository,
                          UserConfigRepository userConfigRepository,
                          ClubManagementRepository clubManagementRepository) {
        this.clubRepository = clubRepository;
        this.humanRepository = humanRepository;
        this.userConfigRepository = userConfigRepository;
        this.clubManagementRepository = clubManagementRepository;
    }

    @GetMapping("get/clubs")
    public List<Club> getClubs() {
        return clubRepository.findAll();
    }

    @PostMapping("get/founder")
    public Human getClubFounder(@RequestBody Integer club_id) {
        return humanRepository.getHumanByClub(club_id);
    }

    @PostMapping("get/participants")
    public List<Human> getClubParticipants(@RequestBody Integer clubId) {
        return humanRepository.findAllByClubId(clubId);
    }

    @GetMapping("get/participation/clubs")
    public List<Club> getParticipationClubs(@RequestAttribute(name = "Uid") Integer UID) {
        return clubRepository.findParticipationClubs(UID);
    }

    @GetMapping("get/management/clubs")
    public List<Club> getManagementClubs(@RequestAttribute(name = "Uid") Integer UID) {
        return clubRepository.findManagementClubs(UID);
    }

    @PostMapping("add/new")
    public ResponseEntity<?> addClub(@RequestBody Club club, @RequestAttribute(name = "Uid") Integer UID) {

        Human human = humanRepository.getHumanByConfig(UID);
        club.setId_founder(human);
        Club savedClub = clubRepository.save(club);
        Optional<UserConfig> configOptional =  userConfigRepository.findById(Long.valueOf(UID));
        if (configOptional.isPresent()) {
            ClubManagement clubManagement = new ClubManagement();
            clubManagement.setId_config(configOptional.get());
            clubManagement.setId_club(savedClub);
            clubManagement.setRole("owner");
            clubManagementRepository.save(clubManagement);
            return ResponseEntity.ok().build();
        }
        throw new RuntimeException();
    }

}
