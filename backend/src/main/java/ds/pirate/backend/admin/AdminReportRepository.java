package ds.pirate.backend.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import ds.pirate.backend.entity.reportList;

public interface AdminReportRepository extends JpaRepository<reportList, Long>, QuerydslPredicateExecutor<reportList> {
    
}
