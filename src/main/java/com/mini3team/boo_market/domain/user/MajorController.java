package com.mini3team.boo_market.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MajorController {

    private final MajorRepository majorRepository;

    @GetMapping("/api/majors")
    public ResponseEntity<?> getMajors() {
        List<Major> majors = majorRepository.findAll();
        return ResponseEntity.ok(Map.of(
                "data", majors
        ));
    }
}
