package ds.pirate.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.HashTags;
@Repository
public interface HashTagRepository extends JpaRepository<HashTags, Long>{
    
    @Query("select h from HashTags h where articles_id=:aid ")
    List<HashTags> getList(Long aid);

    Optional<HashTags> findByArticlesAndHashTagName(ArticlesList articles, String hashTagName);

    List<HashTags> findByArticles(ArticlesList articles);

    @Query(value = "select * from hash_tags ha where articles_id=:aid order by hid asc limit 1", nativeQuery = true)
    Optional<HashTags> findOneByArticle(Long aid);

    @Query("SELECT ht FROM HashTags ht WHERE hash_tag_name like %:keyword%")
    Optional<List<HashTags>> getAidListByHashTagName(String keyword);

    Page<HashTags> findByHashTagNameContainsIgnoreCaseOrderByHidDesc(String keyword, Pageable pageable);
}
