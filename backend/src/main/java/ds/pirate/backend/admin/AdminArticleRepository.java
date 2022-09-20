package ds.pirate.backend.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import ds.pirate.backend.entity.ArticlesList;

public interface AdminArticleRepository  extends JpaRepository<ArticlesList, Long>, QuerydslPredicateExecutor<ArticlesList> {
    
}
