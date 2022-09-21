package ds.pirate.backend.admin.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.QuestionsList;

@Repository
public interface AdminQuestionRepository  extends JpaRepository<QuestionsList, Long>, QuerydslPredicateExecutor<QuestionsList> {
    
}
