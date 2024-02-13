package com.den.korolev.football_manager.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface ExerciseFileService {
    Path uploadPhoto(MultipartFile photo, Long UID);
    Path uploadVideo(MultipartFile video, Long UID);
}
