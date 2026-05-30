package com.mini3team.boo_market.common.config;

import com.mini3team.boo_market.domain.user.Major;
import com.mini3team.boo_market.domain.user.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MajorDataInitializer implements ApplicationRunner {

    private final MajorRepository majorRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (majorRepository.count() > 0) return;

        majorRepository.saveAll(List.of(
                // 영어대학
                new Major(1, "영미문학·문화학부"),
                new Major(2, "ELLT학과"),
                new Major(3, "LD학부"),

                // 사범대학
                new Major(4, "영어교육과"),

                // 경상대학
                new Major(5, "국제경제학과"),
                new Major(6, "GBT학부"),
                new Major(7, "국제금융학과"),

                // 사회과학대학
                new Major(8, "행정학과"),
                new Major(9, "미디어커뮤니케이션학부"),
                new Major(10, "국제학부"),

                // 자연과학대학
                new Major(11, "수학과"),
                new Major(12, "통계학과"),
                new Major(13, "전자물리학과"),
                new Major(14, "화학과"),

                // 공과대학
                new Major(15, "컴퓨터공학부"),
                new Major(16, "산업경영공학부"),
                new Major(17, "기계전자공학부"),
                new Major(18, "바이오메디컬공학부"),

                // 인문대학
                new Major(19, "철학과"),
                new Major(20, "역사문화학부"),
                new Major(21, "언어인지과학과"),

                // 법과대학
                new Major(22, "법학부"),

                // 국제지역대학 - 서양학부
                new Major(23, "서양학부(영어권)"),
                new Major(24, "서양학부(프랑스어권)"),
                new Major(25, "서양학부(독일어권)"),
                new Major(26, "서양학부(러시아어권)"),

                // 국제지역대학 - 동양학부
                new Major(27, "동양학부(일본어전공)"),
                new Major(28, "동양학부(중국어전공)"),
                new Major(29, "동양학부(말레이인도네시아어전공)"),
                new Major(30, "동양학부(태국어전공)"),
                new Major(31, "동양학부(아랍어전공)"),
                new Major(32, "동양학부(한국학전공)"),

                // 국제지역대학 - 기타
                new Major(33, "동유럽발칸학부"),
                new Major(34, "중앙아시아학부"),
                new Major(35, "아프리카학부"),
                new Major(36, "중동외교학과"),
                new Major(37, "브라질학과"),
                new Major(38, "아르헨티나학과")
        ));
    }
}
