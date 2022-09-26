package ds.pirate.backend.repository;

import java.util.List;
import java.util.Optional;

import ds.pirate.backend.vo.MySubInfo;
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

    @Query(value = "SELECT u.userid as myuserid, ss.target_id AS userid, uu.air_name AS airName, po.post, f.following AS following, fo.follower AS follower " +
            "FROM air_user u " +
            "LEFT JOIN subscrib_list ss ON u.userid = ss.userid_userid " +
            "LEFT JOIN (SELECT u.air_name, u.userid FROM air_user u  ) uu ON ss.target_id = uu.userid " +
            "LEFT JOIN (SELECT u.userid, COUNT(a.aid) AS post FROM air_user u LEFT JOIN articles_list a on u.userid =  a.a_user group by u.userid) po ON po.userid=ss.target_id  " +
            "LEFT JOIN (SELECT u.userid, COUNT(s.target_id) AS following FROM air_user u LEFT JOIN subscrib_list s ON u.userid = s.userid_userid GROUP BY u.userid) f ON f.userid = ss.target_id " +
            "LEFT JOIN (SELECT u.userid, COUNT(s.userid_userid) AS follower FROM air_user u LEFT JOIN subscrib_list s ON u.userid = s.target_id GROUP BY u.userid) fo ON fo.userid = ss.target_id " +
            "WHERE u.userid = :userid " +
            "GROUP BY ss.target_id " +
            "ORDER BY follower DESC;", nativeQuery = true)
    Optional<List<getMySubInfo>> getPostFollwerFollwingInSubByUserid(Long userid);

    @Query(value = "SELECT s.userid_userid FROM subscrib_list s WHERE s.userid_userid=:userid AND s.target_id = :targetid",nativeQuery = true)
    Long getFollowingByUseridAndTargetid(Long userid, Long targetid);

    @Query(value = "SELECT * FROM subscrib_list WHERE userid_userid=:userid AND target_id = :targetid",nativeQuery = true)
    Optional<subscribList> getSubListByUseridAndTargetid(Long userid, Long targetid);

    public interface getMySubInfo{
        Long getMyuserid();
        Long getUserid();
        String getAirName();
        Long getPost();
        Long getFollowing();
        Long getFollower();
    }
}