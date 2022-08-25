package ds.pirate.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.ArticlesList;

@Repository
public interface ArticleRepository extends JpaRepository<ArticlesList,String> {
    @EntityGraph(attributePaths = { "tags", "images", "cgroup" }, type = EntityGraphType.LOAD)
    @Query(
        value = "select air from ArticlesList air "
      )
      Page<ArticlesList> getArticleList(Pageable pageable);

}
