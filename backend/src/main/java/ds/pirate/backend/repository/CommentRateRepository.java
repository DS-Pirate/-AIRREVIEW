package ds.pirate.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.acommentRate;

@Repository
public interface CommentRateRepository extends JpaRepository<acommentRate, Long>{
        //했는지 안했는지 여부만 판단 할 것이고 어차피 불러올때 유저 전체의 정보를 불러올 필요가 없기에 엔티티는 Long으로 처리
        @Query("SELECT ctr FROM acommentRate ctr WHERE commentid=:cid and userid=:userid")
        Optional<acommentRate> getIsRatedByCidAndUserid(Long cid, Long userid);
}
