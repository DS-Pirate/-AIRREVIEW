package ds.pirate.backend.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ds.pirate.backend.entity.airUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<airUser, Long> {
    @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
    @Query("select u from airUser u where u.eMail=:email ")
    Optional<airUser> findByEmail(String email);

    @EntityGraph(attributePaths = { "roleSet" }, type = EntityGraphType.LOAD)
    @Query("select u from airUser u where u.eMail=:email and u.auth=:auth ")
    Optional<airUser> findByEmail(String email, boolean auth);

    @Query("select user from airUser user where userid=:userId ")
    Optional<airUser> findByUserId(Long userId);

    @EntityGraph(attributePaths = { "userImg" }, type = EntityGraphType.LOAD)
    @Query("select user from airUser user where userid=:userId")
    Optional<airUser> findByUserIdWithUserImage(Long userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update airUser u set u.passwd=:passwd where u.userid=:userid")
    int changePassbyId(Long userid, String passwd);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update airUser u set u.passwd=:upasswd, u.airName=:name, u.eMail=:email, u.userIntro=:userintro, u.birthDay=:birthday where u.userid=:userid")
    void changePasswdbyIdAndcpass(Long userid, String upasswd, String name,
            String email,
            String userintro, LocalDateTime birthday);

    @Query("select userid from airUser where eMail=:email and (q1=:q1 or q2=:q2 or q3=:q3)")
    Long findUserIdByEmailAndQ(String email, String q1, String q2, String q3);

    @Query("SELECT air.userIntro as userIntro, air.airName as airName, uf.fileName as fileName, air.birthDay as birthDay "+
    "FROM airUser as air left join uImagesList as uf on air.userid=uf.airuser WHERE air.userid=:userid and uf.idx=99")
    Optional<UserIntroPage> getUserInfoByUserId(Long userid);

    public interface UserIntroPage {
        String getUserIntro();
        String getAirName();
        String getFileName();
        LocalDateTime getBirthDay();


        
    }
}
