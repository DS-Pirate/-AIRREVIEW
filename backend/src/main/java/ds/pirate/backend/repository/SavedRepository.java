package ds.pirate.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ds.pirate.backend.entity.SaveList;

public interface SavedRepository extends JpaRepository<SaveList, Long> {

    @Query("SELECT sav FROM SaveList sav WHERE userid=:uid and aid=:aid")
    Optional<SaveList> checkSaveLogByUserIdAndArticleId(Long uid, Long aid);

    @Query("SELECT sav FROM SaveList sav WHERE userid=:uid")
    Optional<List<SaveList>> getList(Long uid);
}