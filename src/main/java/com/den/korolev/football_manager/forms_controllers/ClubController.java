package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import com.den.korolev.football_manager.request_params.Participant;
import jakarta.transaction.Transactional;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/club")
public class ClubController {

    private final ClubRepository clubRepository;
    private final HumanRepository humanRepository;
    private final UserConfigRepository userConfigRepository;
    private final ClubManagementRepository clubManagementRepository;
    private final ApplicationRepository applicationRepository;
    private final PlayerRepository playerRepository;
    private final ClubMembershipRepository clubMembershipRepository;

    public ClubController(ClubRepository clubRepository,
                          HumanRepository humanRepository,
                          UserConfigRepository userConfigRepository,
                          ClubManagementRepository clubManagementRepository,
                          ApplicationRepository applicationRepository,
                          PlayerRepository playerRepository,
                          ClubMembershipRepository clubMembershipRepository) {
        this.clubRepository = clubRepository;
        this.humanRepository = humanRepository;
        this.userConfigRepository = userConfigRepository;
        this.clubManagementRepository = clubManagementRepository;
        this.applicationRepository = applicationRepository;
        this.playerRepository = playerRepository;
        this.clubMembershipRepository = clubMembershipRepository;
    }

    @Transactional
    @PostMapping("leave/from/club")
    public ResponseEntity<?> leaveClub(@RequestBody Integer clubId,
                                       @RequestAttribute(name = "Uid") Integer UID) {
        clubMembershipRepository.deleteByClubAndUser(UID, clubId);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @PostMapping("apply/player")
    public ResponseEntity<?> applyPlayer(@RequestBody List<Integer> list,
                                         @RequestAttribute(name = "Uid") Integer UID) {
        Integer clubId = list.getFirst();
        Integer appId = list.get(1);
        Integer mode = list.get(2);
        applicationRepository.updateClubApplyMode(clubId, appId, mode, UID);
        return ResponseEntity.ok().build();
    }

    @PostMapping("apply/to/club")
    public ResponseEntity<?> applyToClub(@RequestBody Pair<Application, Integer> pair,
                                         @RequestAttribute(name = "Uid") Integer UID) {
        Application application = pair.getFirst();
        Integer id_club = pair.getSecond();
        if (application.getClub_approve() == 0) {
            Player player;
            Club club;
            Optional<Player> optionalPlayer = playerRepository.findById(Long.valueOf(UID));
            Optional<Club> optionalClub = clubRepository.findById(id_club);
            if (optionalPlayer.isPresent() && optionalClub.isPresent()) {
                player = optionalPlayer.get();
                club = optionalClub.get();
                application.setId_player(player);
                application.setId_club(club);
                try {
                    applicationRepository.save(application);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return ResponseEntity.ok().build();
            }
        }
        throw new RuntimeException();
    }

    @GetMapping("get/player/applications")
    public List<Application> getApplicationsByPlayer(@RequestAttribute(name = "Uid") Integer UID) {
        return applicationRepository.findAllByIdPlayer(UID);
    }

    @GetMapping("get/participants/{clubId}")
    public List<Participant> getClubParticipants(@PathVariable Integer clubId,
                                                 @RequestAttribute(name = "Uid") Integer UID) {
        List<Player> players = playerRepository.getClubParticipants(UID, clubId);
        return players.stream().map(player -> new Participant(player, player.getHuman())).collect(Collectors.toList());
    }

    @GetMapping("get/club/applications/{clubId}")
    public List<Application> getApplicationsByClub(@PathVariable Integer clubId,
                                                   @RequestAttribute(name = "Uid") Integer UID) {
        List<Application> apps =  applicationRepository.findAllByIdClub(clubId, UID);
        for (Application app : apps) {
            Player player = app.getId_player();
            Human human = player.getHuman();
            app.setMessage(human.getName() + " " + human.getSurname() + " " + human.getPatronymic()
                    + " " + human.getBirthday().toString());
        }
        return apps;
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
