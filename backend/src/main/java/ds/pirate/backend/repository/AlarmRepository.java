package ds.pirate.backend.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.alarm;

@Repository
public interface AlarmRepository extends JpaRepository<alarm, Long>{
    
    alarm findByArticleId(Long articleId);

    @Query(value = 
            "select "+
            "regdate, "+
            "(select atitle from articles_list ar where am.article_id_aid = ar.aid) as title, "+
            "(select air_name from air_user as ar where am.who_user_userid = ar.userid) as whouser, "+
            "(select air_name from air_user as ar where am.to_user_userid = ar.userid) as touser, "+
            "(select comment_context from acomments as co where am.comment_id_cid = co.cid) as commentContext "+
            "from alarm as am "+
            "where am.to_user_userid =:userid ", 
            nativeQuery = true)
    Optional<List<Object>> getAlarmInfoByUserid(Long userid);

}
