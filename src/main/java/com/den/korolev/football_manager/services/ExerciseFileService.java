package com.den.korolev.football_manager.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface ExerciseFileService {
    Path uploadPhoto(MultipartFile photo, Long UID);
    Path uploadVideo(MultipartFile video, Long UID);
    Resource getPhoto(String path);
    Resource getVideo(String path);
}
