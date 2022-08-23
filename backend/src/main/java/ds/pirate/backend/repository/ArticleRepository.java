package ds.pirate.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.ArticlesList;

@Repository
public interface ArticleRepository extends JpaRepository<ArticlesList,String> {
    
}
