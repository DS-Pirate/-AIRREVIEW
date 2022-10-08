package ds.pirate.backend.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.airUser;
import ds.pirate.backend.entity.uImagesList;

@Repository
public interface UserImageListRepository extends JpaRepository<uImagesList, Long>{
    @Query("select aimgs from uImagesList aimgs where airuser.userid=:userid and idx=0")
    Optional<uImagesList> getByAiruser(Long userid);

    @Query("select aimgs from uImagesList aimgs where airuser.userid=:userid and idx=99")
    Optional<uImagesList> getCHImageByUserId(Long userid);

    @Query("select aimgs from uImagesList aimgs where airuser=:user and idx=0")
    Optional<uImagesList> getByAiruserEntity(airUser user);

}
