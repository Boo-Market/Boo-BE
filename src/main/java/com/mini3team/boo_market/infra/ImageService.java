package com.mini3team.boo_market.infra;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    private final String uploadDir = System.getProperty("user.home") + "/uploads/images";

    public List<String> uploadImages(List<MultipartFile> images) {
        List<String> urls = new ArrayList<>();

        for (MultipartFile image : images) {
            String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path path = Paths.get(uploadDir, fileName);

            try {
                Files.createDirectories(path.getParent());
                Files.copy(image.getInputStream(), path);
                urls.add("/images/" + fileName);
            } catch (IOException e) {
                throw new RuntimeException("이미지 저장 실패", e);
            }
        }
        return urls;
    }
}