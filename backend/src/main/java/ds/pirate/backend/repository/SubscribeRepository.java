package ds.pirate.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.pirate.backend.entity.subscribList;

public interface SubscribeRepository extends JpaRepository<subscribList, Long> {
    @Query("SELECT sb FROM subscribList sb WHERE target_id=:targetid and userid_userid=:userid")
    Optional<subscribList> getIsSubcedByTargetIdAndUserid(Long targetid, Long userid);

    @Query("SELECT count(targetId) FROM subscribList WHERE target_id=:targetid")
    Long getSumByTargetId(Long targetid);

    @Query("SELECT s FROM subscribList s WHERE userid_userid=:userid")
    Optional<List<subscribList>> getByUserId(Long userid);
}