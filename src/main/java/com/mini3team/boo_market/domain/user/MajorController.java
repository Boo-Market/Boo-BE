package com.mini3team.boo_market.domain.user;

import com.mini3team.boo_market.common.response.DataResponse;
import com.mini3team.boo_market.dto.response.MajorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/majors")
public class MajorController {
    private final MajorRepository majorRepository;

    @GetMapping
    public DataResponse<List<MajorResponse>> findMajors() {
        return new DataResponse<>(
                majorRepository.findAll().stream()
                        .map(major -> new MajorResponse(major.getId(), major.getName()))
                        .toList()
        );
    }
}
