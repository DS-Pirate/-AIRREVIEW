package ds.pirate.backend.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import ds.pirate.backend.entity.airUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<airUser, Long>{
    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraphType.LOAD)
    @Query("select u from airUser u where u.eMail=:email ")
    Optional<airUser> findByEmail(String email);


    @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraphType.LOAD)
    @Query("select u from airUser u where u.eMail=:email and u.auth=:auth ")
    Optional<airUser> findByEmail(String email, boolean auth);
    
    @Query("select user from airUser user where userid=:userId ")
    Optional<airUser> findByUserId(Long userId);

    @Query("select userid from airUser where eMail=:email and (q1=:q1 or q2=:q2 or q3=:q3)")
    Long findUserIdByEmailAndQ(String email, String q1, String q2, String q3);
}
