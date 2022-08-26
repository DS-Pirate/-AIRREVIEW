package ds.pirate.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ds.pirate.backend.entity.HashTags;
@Repository
public interface HashTagRepository extends JpaRepository<HashTags, Long>{
    
    @Query("select h from HashTags h where articles_id=:aid ")
    List<HashTags> getList(Long aid);
}
