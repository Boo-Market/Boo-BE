package com.mini3team.boo_market.infra;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @Operation(summary = "이미지 업로드")
    @PostMapping(value = "/api/images/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImages(
            @RequestBody(content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE))
            @org.springframework.web.bind.annotation.RequestParam("images") List<MultipartFile> images) {

        List<String> urls = imageService.uploadImages(images);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "data", Map.of("imageUrls", urls)
        ));
    }
}