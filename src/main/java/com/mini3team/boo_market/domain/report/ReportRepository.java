package com.mini3team.boo_market.domain.report;

import com.mini3team.boo_market.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    void deleteAllByReporter(User reporter);
    void deleteAllByTargetUser(User targetUser);
}
