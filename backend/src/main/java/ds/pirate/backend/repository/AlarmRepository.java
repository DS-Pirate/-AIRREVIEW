package ds.pirate.backend.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.ArticlesList;
import ds.pirate.backend.entity.alarm;

@Repository
public interface AlarmRepository extends JpaRepository<alarm, Long>{
    
    @Query(value=
            "select (select air_name from air_user as ar where am.who_user_userid = ar.userid) as whouser, air_name as touserm, am.article_id_aid as aid, atitle as title, comment_context, am.regdate, am.checked, am.arid "+
            "from alarm as am left join air_user as ar on am.to_user_userid = ar.userid left join articles_list as atc on am.article_id_aid=atc.aid left join acomments as ac on am.comment_id_cid = ac.cid "+
            "where am.to_user_userid =:userid order by arid desc limit 5"
            , nativeQuery = true)
    Optional<List<Object>> getAlarmInfoByUserid(Long userid);

    @Query("select arm from alarm arm where comment_id_cid=:cid")
    Optional<alarm> getByCid(Long cid);

    @Query("select arm from alarm arm where arm.articleId=:articleId")
    List<alarm> findByArticleId(ArticlesList articleId);
}
