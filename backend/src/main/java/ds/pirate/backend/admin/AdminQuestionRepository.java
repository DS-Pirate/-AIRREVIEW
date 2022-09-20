package ds.pirate.backend.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import ds.pirate.backend.entity.QuestionsList;

public interface AdminQuestionRepository  extends JpaRepository<QuestionsList, Long>, QuerydslPredicateExecutor<QuestionsList> {
    
}
