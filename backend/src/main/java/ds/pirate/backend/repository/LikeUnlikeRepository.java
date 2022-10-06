package ds.pirate.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.likeUnlikeList;

public interface LikeUnlikeRepository extends JpaRepository<likeUnlikeList, Long> {

    @Query("SELECT lin FROM likeUnlikeList lin WHERE userid=:uid and aid=:aid")
    Optional<likeUnlikeList> checkFavoLogByUserIdAndArticleId(Long uid, Long aid);

    @Query("SELECT lin FROM likeUnlikeList lin WHERE userid=:uid")
    Optional<List<likeUnlikeList>> getList(Long uid);

    @Query("SELECT count(lin) FROM likeUnlikeList lin WHERE userid=:uid and aid=:aid")
    Optional<Integer> checkFavoLogByUserIdAndArticleId2(Long uid, Long aid);

    @EntityGraph(attributePaths = { "tags", "images", "cgroup" }, type = EntityGraphType.LOAD)
    @Query(value = "select air from ArticlesList air ")
    Page<ArticlesList> getArticleList(Pageable pageable);

    ArticlesList getByAid(Long aid);
}