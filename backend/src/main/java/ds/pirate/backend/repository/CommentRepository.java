package ds.pirate.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.acomments;
import ds.pirate.backend.entity.airUser;

public interface CommentRepository extends JpaRepository<acomments, Long> {

    @Query("SELECT ct FROM acomments ct WHERE articles_aid=:aid ORDER BY comment_group asc, comment_sorts asc, commnet_depth desc ")
    Optional<List<acomments>> getListByAid(Long aid);

    @Query("SELECT ct FROM acomments ct WHERE ct.cid=:cid and airuser_userid=:userid ")
    Optional<acomments> getCommentByCidAndUserid(Long cid, Long userid);

    @Query("SELECT AVG(articleRate) FROM acomments ct WHERE articles_aid=:aid and articleRate>0")
    Double getAvgByAid(Long aid);

    Optional<acomments> findByAiruser(airUser airuser);

    @Query("SELECT ct FROM acomments ct WHERE articles_aid=:aid and airuser_userid=:userid and commnet_depth=0 ")
    Optional<acomments> getCommentByAidAndUserid(ArticlesList aid, airUser userid);

    @Query("SELECT commentGroup FROM acomments WHERE articles_aid=:aid ORDER BY comment_group desc")
    Optional<List<Long>> getLatestCommentGroupWhereMatchWithAid(Long aid);

    @Query(value = "SELECT ct FROM acomments ct WHERE articles_aid=:aid ORDER BY comment_group asc, comment_sorts asc, commnet_depth desc ", countQuery = "select count(ct) from acomments ct WHERE articles_aid=:aid ORDER BY comment_group asc, comment_sorts asc, commnet_depth desc ")
    Page<acomments> getPageList(Pageable pageable, Long aid);

    @Query(value = "SELECT * "+ 
        "FROM acomments ct left join articles_list att on ct.articles_aid = att.aid " +
        "WHERE att.a_user =:authorid and ct.airuser_userid=:userid order by cid desc limit 5"
        ,nativeQuery = true)
    Optional<List<acomments>> getListByUserIdAndAuthorId(Long userid, Long authorid);
}
