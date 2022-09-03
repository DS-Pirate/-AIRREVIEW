package ds.pirate.backend.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.pirate.backend.entity.acomments;

public interface CommentRepository extends JpaRepository<acomments, Long>{
    

    @Query("SELECT ct FROM acomments ct WHERE articles_aid=:aid ORDER BY comment_group asc, comment_sorts asc, commnet_depth desc ")
    Optional<List<acomments>> getListByAid(Long aid);

    @Query("SELECT ct FROM acomments ct WHERE ct.cid=:cid and airuser_userid=:userid ")
    Optional<acomments> getCommentByCidAndUserid(Long cid, Long userid);

    @Query("SELECT AVG(articleRate) FROM acomments ct WHERE articles_aid=:aid ")
    Double getAvgByAid(Long aid);
}
