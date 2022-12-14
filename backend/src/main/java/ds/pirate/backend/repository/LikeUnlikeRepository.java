package ds.pirate.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.pirate.backend.entity.likeUnlikeList;

public interface LikeUnlikeRepository extends JpaRepository<likeUnlikeList, Long> {

    @Query("SELECT lin FROM likeUnlikeList lin WHERE userid=:uid and aid=:aid")
    Optional<likeUnlikeList> checkFavoLogByUserIdAndArticleId(Long uid, Long aid);

    @Query("SELECT lin FROM likeUnlikeList lin WHERE userid=:userid")
    Optional<List<likeUnlikeList>> getList(Long userid);

    @Query("SELECT count(lin) FROM likeUnlikeList lin WHERE userid=:uid and aid=:aid")
    Optional<Integer> checkFavoLogByUserIdAndArticleId2(Long uid, Long aid);
}