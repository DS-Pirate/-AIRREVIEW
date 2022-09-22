package ds.pirate.backend.admin.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.ArticlesList;

@Repository
public interface AdminArticleRepository  extends JpaRepository<ArticlesList, Long>, QuerydslPredicateExecutor<ArticlesList> {
    
    @Query("select att from ArticlesList att where aUser =:userid")
    Optional<List<ArticlesList>> getArticlesListByUserid(Long userid);
}
