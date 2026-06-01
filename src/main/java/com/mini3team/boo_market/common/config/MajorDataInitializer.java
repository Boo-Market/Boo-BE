package com.mini3team.boo_market.common.config;

import com.mini3team.boo_market.domain.category.Category;
import com.mini3team.boo_market.domain.category.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (categoryRepository.count() == 0) {
            categoryRepository.saveAll(List.of(
                    new Category(1L, "전공책"),
                    new Category(2L, "교양책"),
                    new Category(3L, "생활용품"),
                    new Category(4L, "분실물"),
                    new Category(5L, "대여"),
                    new Category(6L, "기타")
            ));
        }

        if (majorRepository.count() > 0) return;

        majorRepository.saveAll(List.of(
                // 인문대학
                new Major(1, "철학과"),
                new Major(2, "사학과"),
                new Major(3, "언어인지과학과"),

                // 국가전략언어대학
                new Major(4, "폴란드학과"),
                new Major(5, "루마니아학과"),
                new Major(6, "체코·슬로바키아학과"),
                new Major(7, "헝가리학과"),
                new Major(8, "세르비아·크로아티아학과"),
                new Major(9, "그리스·불가리아학과"),
                new Major(10, "중앙아시아학과"),
                new Major(11, "아프리카학부"),
                new Major(12, "우크라이나학과"),
                new Major(13, "한국학과"),

                // 경상대학
                new Major(14, "Global Business & Technology학부"),
                new Major(15, "국제금융학과"),

                // 자연과학대학
                new Major(16, "수학과"),
                new Major(17, "통계학과"),
                new Major(18, "전자물리학과"),
                new Major(19, "환경학과"),
                new Major(20, "생명공학과"),
                new Major(21, "화학과"),

                // 공과대학
                new Major(22, "컴퓨터공학부"),
                new Major(23, "정보통신공학과"),
                new Major(24, "반도체전자공학부(반도체공학전공)"),
                new Major(25, "반도체전자공학부(전자공학전공)"),
                new Major(26, "산업경영공학과"),

                // 융합인재대학
                new Major(27, "융합인재학부"),

                // Culture & Technology융합대학
                new Major(28, "디지털콘텐츠학부"),
                new Major(29, "투어리즘 & 웰니스학부"),
                new Major(30, "글로벌스포츠산업학부"),

                // AI융합대학
                new Major(31, "AI데이터융합학부"),
                new Major(32, "Finance & AI융합학부"),

                // 독립학부
                new Major(33, "바이오메디컬공학부"),
                new Major(34, "기후변화융합학부"),
                new Major(35, "자유전공학부(글로벌)"),

                // 계열·단과대학 통합모집
                new Major(36, "인문대학[통합모집]"),
                new Major(37, "국가전략언어계열"),
                new Major(38, "경상대학[통합모집]"),
                new Major(39, "자연과학대학[통합모집]"),
                new Major(40, "공과계열"),
                new Major(41, "Culture & Technology융합대학[통합모집]"),
                new Major(42, "AI융합대학[통합모집]")
        ));
    }
}
