package ds.pirate.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.acommentRate;

@Repository
public interface CommentRateRepository extends JpaRepository<acommentRate, Long>{
        @Query("SELECT ctr FROM acommentRate ctr WHERE commentid=:cid and userid=:userid")
        Optional<acommentRate> getIsRatedByCidAndUserid(Long cid, Long userid);
}
