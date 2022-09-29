package ds.pirate.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.ImagesList;

public interface ImageRepository extends JpaRepository<ImagesList, Long>{








    Optional<ImagesList> findFirstByArticlesOrderByIidAsc(ArticlesList article);
}
