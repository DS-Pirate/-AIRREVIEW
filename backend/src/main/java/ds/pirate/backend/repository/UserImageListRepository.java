package ds.pirate.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.uImagesList;

@Repository
public interface UserImageListRepository extends JpaRepository<uImagesList, Long>{
    @Query("select aimgs from uImagesList aimgs where airuser.userid=:userid")
    uImagesList getByAiruser(Long userid);
}
