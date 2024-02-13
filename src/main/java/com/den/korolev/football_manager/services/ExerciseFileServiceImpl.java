package com.den.korolev.football_manager.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Service
public class ExerciseFileServiceImpl implements ExerciseFileService {

    @Override
    public Path uploadPhoto(MultipartFile imageFile, Long UID) {

        if (imageFile != null) {

            Properties properties = new Properties();
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream("src/main/resources/user.properties");
                properties.load(inputStream);

                String uploadDir = properties.getProperty("server.photo.storage") + "user" + UID + "//";
                File dir = new File(uploadDir);
                if (!dir.exists())
                    dir.mkdirs();
                Path imagePath = Paths.get(uploadDir + imageFile.getOriginalFilename());
                return Files.write(imagePath, imageFile.getBytes());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public Path uploadVideo(MultipartFile videoFile, Long UID) {

        if (videoFile != null) {

            Properties properties = new Properties();
            FileInputStream inputStream;
            try {
                inputStream = new FileInputStream("src/main/resources/user.properties");
                properties.load(inputStream);

                String uploadDir = properties.getProperty("server.video.storage") + "user" + UID + "//";
                File dir = new File(uploadDir);
                if (!dir.exists())
                    dir.mkdirs();
                Path videoPath = Paths.get(uploadDir + videoFile.getOriginalFilename());
                return Files.write(videoPath, videoFile.getBytes());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
