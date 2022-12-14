package ds.pirate.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.pirate.backend.entity.subscribList;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<subscribList, Long> {
    @Query("SELECT sb FROM subscribList sb WHERE target_id=:targetid and userid_userid=:userid")
    Optional<subscribList> getIsSubcedByTargetIdAndUserid(Long targetid, Long userid);

    @Query("SELECT count(targetId) FROM subscribList WHERE target_id=:targetid")
    Long getSumByTargetId(Long targetid);

    @Query("SELECT s FROM subscribList s WHERE userid_userid=:userid")
    Optional<List<subscribList>> getByUserId(Long userid);

    @Query(value = "SELECT u.userid as myuserid, ss.target_id AS userid, uu.air_name AS airName, " +
            "            i.file_name AS fileName, i.idx as idx   " +
            "            FROM air_user u LEFT JOIN subscrib_list ss ON u.userid = ss.userid_userid " +
            "            LEFT JOIN (SELECT u.air_name, u.userid, u.user_intro FROM air_user u) uu ON ss.target_id = uu.userid  " +
            "            LEFT JOIN u_images_list i ON uu.userid = i.airuser\n" +
            "            WHERE u.userid = 42 AND (i.iid IN  (SELECT Max(i.iid) FROM u_images_list i WHERE i.idx = 0 or i.idx = 99 GROUP BY i.airuser) OR i.iid IS NULL) " +
            "            GROUP BY ss.target_id, i.iid ", nativeQuery = true)
    Optional<List<getMySubInfo>> getSubInfoAndImgByUserId(Long userid);

    @Query(value = "SELECT u.userid as myuserid, uu.user_intro as userintro , ss.target_id AS userid, uu.air_name AS airName, po.post, f.following AS following, fo.follower AS follower, " +
            "i.file_name AS fileName, i.idx as idx " +
            "FROM air_user u LEFT JOIN subscrib_list ss ON u.userid = ss.userid_userid " +
            "LEFT JOIN (SELECT u.air_name, u.userid, u.user_intro FROM air_user u) uu ON ss.target_id = uu.userid " +
            "LEFT JOIN u_images_list i ON uu.userid = i.airuser " +
            "LEFT JOIN (SELECT u.userid, COUNT(a.aid) AS post FROM air_user u LEFT JOIN articles_list a on u.userid =  a.a_user group by u.userid) po ON po.userid=ss.target_id  " +
            "LEFT JOIN (SELECT u.userid, COUNT(s.target_id) AS following FROM air_user u LEFT JOIN subscrib_list s ON u.userid = s.userid_userid GROUP BY u.userid) f ON f.userid = ss.target_id " +
            "LEFT JOIN (SELECT u.userid, COUNT(s.userid_userid) AS follower FROM air_user u LEFT JOIN subscrib_list s ON u.userid = s.target_id GROUP BY u.userid) fo ON fo.userid = ss.target_id " +
            "WHERE u.userid = :userid AND (i.iid IN  (SELECT Max(i.iid) FROM u_images_list i WHERE i.idx = 0 or i.idx = 99 GROUP BY i.airuser) OR i.iid IS NULL)" +
            "GROUP BY ss.target_id, i.iid " , nativeQuery = true)
    Optional<Page<getMySubInfo>> getPostFollwerFollwingInSubByUserid(Long userid, Pageable pageable);

    @Query(value = "SELECT u.userid as myuserid, uu.user_intro as userintro, ss.userid_userid AS userid, uu.air_name AS airName, po.post, f.following AS following, fo.follower AS follower, i.file_name AS fileName, i.idx " +
            "   FROM air_user u " +
            "      LEFT JOIN subscrib_list ss ON u.userid = ss.target_id " +
            "      LEFT JOIN u_images_list i ON ss.userid_userid = i.airuser " +
            "      LEFT JOIN (SELECT air_name, userid, user_intro FROM air_user ) uu ON ss.userid_userid = uu.userid " +
            "      LEFT JOIN (SELECT u.userid, COUNT(a.aid) AS post FROM air_user u LEFT JOIN articles_list a on u.userid =  a.a_user group by u.userid) po ON po.userid=ss.userid_userid " +
            "      LEFT JOIN (SELECT u.userid, COUNT(s.target_id) AS following FROM air_user u LEFT JOIN subscrib_list s ON u.userid = s.userid_userid GROUP BY u.userid) f ON f.userid = ss.userid_userid " +
            "      LEFT JOIN (SELECT u.userid, COUNT(s.userid_userid) AS follower FROM air_user u LEFT JOIN subscrib_list s ON u.userid = s.target_id GROUP BY u.userid) fo ON fo.userid = ss.userid_userid " +
            "   WHERE u.userid = :userid AND (i.iid IN (SELECT Max(iid) FROM u_images_list WHERE i.idx = 0 or i.idx = 99 GROUP BY airuser) OR i.iid IS NULL ) " +
            "   GROUP BY  ss.userid_userid, i.iid" , nativeQuery = true)
    Optional<Page<getMySubInfo>> getPostFollwerFollwingInFollowerByUserid(Long userid, Pageable pageable);

    @Query(value = "SELECT s.userid_userid FROM subscrib_list s WHERE s.userid_userid=:userid AND s.target_id = :targetid",nativeQuery = true)
    Long getFollowingByUseridAndTargetid(Long userid, Long targetid);

    @Query(value = "SELECT * FROM subscrib_list WHERE userid_userid=:userid AND target_id = :targetid",nativeQuery = true)
    Optional<subscribList> getSubListByUseridAndTargetid(Long userid, Long targetid);

    @Query(value = "SELECT f.following as following, fo.follower as follower " +
            "FROM (SELECT COUNT(sbid) AS following FROM subscrib_list l WHERE l.userid_userid =:userid) f, " +
            "(SELECT COUNT(sbid) AS follower FROM subscrib_list l WHERE l.target_id =:userid) fo ",nativeQuery = true)
    Optional<getMySubInfo> getFollwerFollwingCountByUserid(Long userid);

    @Query("select sl from subscribList sl where sl.targetId=:userid")
    Optional<List<subscribList>> getByuserid(Long userid);


    public interface getMySubInfo{
        Long getMyuserid();
        Long getUserid();
        String getAirName();
        Long getPost();
        Long getFollowing();
        Long getFollower();
        String getUserintro();
        String getFileName();
        Long getIdx();
    }
}