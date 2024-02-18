package com.den.korolev.football_manager.forms_controllers;

import com.den.korolev.football_manager.entities.*;
import com.den.korolev.football_manager.request_params.ExercisesMatchRequest;
import com.den.korolev.football_manager.services.ExerciseFileService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseRepository exerciseRepository;
    private final TrainingTargetRepository trainingTargetRepository;
    private final PlayerRepository playerRepository;
    private final ExerciseFileService exerciseFileService;
    private final TrainingRepository trainingRepository;

    public ExerciseController(ExerciseRepository exerciseRepository, TrainingTargetRepository trainingTargetRepository,
                              PlayerRepository playerRepository, ExerciseFileService exerciseFileService,
                              TrainingRepository trainingRepository) {
        this.exerciseRepository = exerciseRepository;
        this.trainingTargetRepository = trainingTargetRepository;
        this.playerRepository = playerRepository;
        this.exerciseFileService = exerciseFileService;
        this.trainingRepository = trainingRepository;
    }

    @PostMapping("to/training")
    public ResponseEntity<?> attachToTraining(@RequestBody ExercisesMatchRequest exercisesMatchRequest) {
        Long trainingId = exercisesMatchRequest.getEventId();
        Long[] exercisesIds = exercisesMatchRequest.getExercisesIds();

        for (Long exercisesId : exercisesIds) {
            TrainingTarget trainingTarget = new TrainingTarget();
            Optional<Training> optionalTraining = trainingRepository.findById(trainingId);
            if (optionalTraining.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ошибка сервера, тренировка не найдена");
            Training training = optionalTraining.get();
            Optional<Exercise> optionalExercise = exerciseRepository.findById(exercisesId);
            if (optionalExercise.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ошибка сервера, упражнение не найдено");
            Exercise exercise = optionalExercise.get();
            trainingTarget.setTraining(training);
            trainingTarget.setExercise(exercise);
            trainingTargetRepository.save(trainingTarget);
        }
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Added exercises\"}");
    }

    @GetMapping("get/exercises")
    public List<Exercise> getExercises(@RequestAttribute(name = "Uid") Long UID) {
        return exerciseRepository.findAllByUserID(UID);
    }

    @Transactional
    @PostMapping("get/event/exercises")
    public List<Exercise> getEventExercises(@RequestBody Integer event_id, @RequestAttribute(name = "Uid") Integer UID) {
        return exerciseRepository.find_exercises_by_event(event_id, UID);
    }

    @PostMapping("add/photo")
    public ResponseEntity<?> uploadPhoto(@RequestParam("image") MultipartFile multipartFile,
                                         @RequestAttribute(name = "Uid") Long UID, HttpSession session) {
        if (multipartFile != null) {
            Path photoPath = exerciseFileService.uploadPhoto(multipartFile, UID);
            session.setAttribute("photo", photoPath.toString());
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Saved photo\"}");
        }
        System.out.println("NULL PHOTO");
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"No photo uploaded\"}");
    }

    @PostMapping("get/photo")
    public Resource loadPhoto(@RequestBody Integer exercise_id, @RequestAttribute(name = "Uid") Long UID) {
        String photoLink = exerciseRepository.findPhotoByIdAndPlayer(exercise_id, UID);
        return exerciseFileService.getPhoto(photoLink);
    }

    @PostMapping("get/video")
    public Resource loadVideo(@RequestBody Integer exercise_id, @RequestAttribute(name = "Uid") Long UID) {
        //HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        String videoLink = exerciseRepository.findVideoByIdAndPlayer(exercise_id, UID);
        Resource resource =  exerciseFileService.getVideo(videoLink);
        return resource;
    }

    @PostMapping("add/video")
    public ResponseEntity<?> uploadVideo(@RequestParam("video") MultipartFile multipartFile,
                                         @RequestAttribute(name = "Uid") Long UID, HttpSession session) {
        if (multipartFile != null) {
            Path videoPath = exerciseFileService.uploadVideo(multipartFile, UID);
            session.setAttribute("video", videoPath.toString());
            return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Saved video\"}");
        }
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"No video uploaded\"}");
    }


    @PostMapping("add/new")
    public ResponseEntity<?> addExercise(@RequestBody Exercise exercise,
                                         @RequestAttribute(name = "Uid") Long UID,
                                         HttpSession session) {
        exercise.setPhoto_link((String) session.getAttribute("photo"));
        exercise.setVideo_link((String) session.getAttribute("video"));
        Optional<Player> optionalPlayer = playerRepository.findById(UID);
        if (optionalPlayer.isEmpty())
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка сервера");
        Player player = optionalPlayer.get();
        exercise.setPlayer(player);
        exerciseRepository.save(exercise);
        return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Saved exercise\"}");
    }
}
